package com.fh.loginInterceptor;


import com.fh.annotation.Ignore;
import com.fh.common.Const;
import com.fh.common.MyException;
import com.fh.member.model.Member;
import com.fh.member.service.MemberService;
import com.fh.util.JwtUtil;
import com.fh.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;


public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //处理客户端传过来的信息头
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-auth,mtoken,content-type");
        //处理客户端发过来的put, delete
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "PUT,DELETE,POST,GET");
        //放过带自定义注解的请求
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(Ignore.class)){
            return true;
        }
        //验证token, token不正确跳转到登录界面
        Member member = null;
        String token = request.getHeader("x-auth");
        //token判空++++++
        if(StringUtils.isEmpty(token)){
            throw new MyException();
        }

        boolean isExist = redisUtil.hasKey(token);
        if(!isExist){
            throw new MyException();
        }

        boolean verify = JwtUtil.verify(token);
        if(verify){
            String id = JwtUtil.getUserId(token);
            member = memberService.findMemberById(Integer.valueOf(id));
        }else{
            throw new MyException();
        }
        //如果用户为空则没登录，跳转到登录界面
        if(member == null){
            throw new MyException();
        }
        //当前登录用户
        request.getSession().setAttribute(Const.SESSION_KEY, member);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
