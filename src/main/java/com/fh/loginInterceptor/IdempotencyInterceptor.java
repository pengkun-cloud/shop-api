package com.fh.loginInterceptor;

import com.fh.annotation.Idempotency;
import com.fh.common.MyException;
import com.fh.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

public class IdempotencyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(!method.isAnnotationPresent(Idempotency.class)){
            return true;
        }
        //验证幂等性
        String idempotencyToken = request.getHeader("idempotencyToken");
        if(StringUtils.isEmpty(idempotencyToken)){
            throw new MyException("幂等性头信息丢失");
        }

        boolean hasIdempotencyToken = redisUtil.hasKey(idempotencyToken);
        if(!hasIdempotencyToken){
            throw new MyException("没有幂等性头信息");
        }

        Boolean res = redisUtil.delSingleKey(idempotencyToken);
        if(!res){
            throw new MyException("幂等性头信息失效");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
