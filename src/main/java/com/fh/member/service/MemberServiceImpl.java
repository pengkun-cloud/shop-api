package com.fh.member.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.Const;
import com.fh.common.ServerResponse;
import com.fh.member.mapper.MemberMapper;
import com.fh.member.message.SendMsg;
import com.fh.member.model.Member;
import com.fh.util.JwtUtil;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.beans.Transient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisUtil redisUtil;


    //验证姓名唯一
    @Override
    public ServerResponse checkMemberName(String name) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Member member = memberMapper.selectOne(queryWrapper);
        System.out.println(name);
        if(member != null){
            return ServerResponse.error("用户已存在");
        }

        return ServerResponse.success();
    }

    //验证手机号唯一
    @Override
    public ServerResponse checkMemberPhone(String phone) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Member member = memberMapper.selectOne(queryWrapper);
        if(member!= null){
            return ServerResponse.error("手机号已存在");
        }
        return ServerResponse.success();
    }

    //注册用户
    @Transient
    @Override
    public ServerResponse register(Member member) {

        memberMapper.insert(member);

        return ServerResponse.success();
    }
    //通过名称查询用户
    @Override
    public Member getMemberByName(String name) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Member member = memberMapper.selectOne(queryWrapper);
        return member;
    }
    //通过id查询用户
    @Override
    public Member findMemberById(Integer id) {

        Member member = memberMapper.selectById(id);
        return member;
    }
    //发送验证码
    @Override
    public ServerResponse sendCode(String phone) {
        String code = SendMsg.getCode();
        try {
            SendSmsResponse sendSmsResponse = SendMsg.sendSms(phone, code);
            //只有当code = ok时才写入redis
            if(sendSmsResponse != null && "OK".equals(sendSmsResponse.getCode())){
                redisUtil.set(phone, code, Const.REDIS_CODE_EXPIRE_TIME);
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return ServerResponse.error(e.getErrMsg());
        }
        return ServerResponse.success(code);
    }
    //用户登录
    @Override
    public ServerResponse login(Member member, HttpServletResponse response) {
        //select * from t_member where name='' or phone =''
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",member.getName());
        queryWrapper.or();
        queryWrapper.eq("phone",member.getName());
        Member memberDB = memberMapper.selectOne(queryWrapper);
        //Member memberDB = memberMapper.queryMemberByNameOrPhone(member);
        if(memberDB == null){
            return ServerResponse.error("用户不存在");
        }
        if(!memberDB.getPassword().equals(member.getPassword())){
            return ServerResponse.error("密码不正确");
        }
        //String encodeTokenMemberId = URLEncoder.encode(memberDB.getId().toString(), "utf-8");
        String tokenMemberId = JwtUtil.sign(memberDB.getName(), memberDB.getId().toString());
        redisUtil.set(tokenMemberId, tokenMemberId, Const.COOKIE_EXPIRE_TIME);
        return ServerResponse.success(tokenMemberId);
    }

}
