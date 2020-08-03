package com.fh.member.controller;

import com.fh.annotation.Ignore;
import com.fh.common.Const;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.member.service.MemberService;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisUtil redisUtil;

    //验证用户名唯一
    @RequestMapping("checkMemberName")
    @Ignore
    public ServerResponse checkMemberName(String name){
        return memberService.checkMemberName(name);
    }

    //验证手机号唯一
    @RequestMapping("checkMemberPhone")
    @Ignore
    public ServerResponse checkMemberPhone(String phone){

        return memberService.checkMemberPhone(phone);
    }

    //发送验证码
    @RequestMapping("sendCode")
    @Ignore
    public ServerResponse sendCode(String phone){
        return memberService.sendCode(phone);
    }

    //用户注册
    @Ignore
    @RequestMapping("register")
    public ServerResponse register(Member member){
        //从redis中取出验证码并判断是否过期
        String redisCode = (String) redisUtil.get(member.getPhone());

        if(redisCode == null){
            return ServerResponse.error("验证码已过期");
        }

        if(!redisCode.equals(member.getCode())){
            return ServerResponse.error("验证码不正确");
        }
        //注册用户
        return memberService.register(member);
    }

    //用户登录
    @Ignore
    @RequestMapping("login")
    public ServerResponse login(Member member, HttpServletResponse response) {
        return memberService.login(member,response);

    }

    //验证用户是否登录
    @RequestMapping("checkMemberLogin")
    public ServerResponse checkMemberLogin(HttpServletRequest request){

        Member member = (Member) request.getSession().getAttribute(Const.SESSION_KEY);
        if(member == null){
            return ServerResponse.error();
        }
        return ServerResponse.success();
    }

    //用户退出
    @RequestMapping("quit")
    public ServerResponse quit(HttpServletRequest request){
        String token = request.getHeader("x-auth");
        redisUtil.del(token);
        return ServerResponse.success();
    }
}
