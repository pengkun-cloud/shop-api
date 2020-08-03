package com.fh.member.service;

import com.fh.common.ServerResponse;
import com.fh.member.model.Member;

import javax.servlet.http.HttpServletResponse;

public interface MemberService {

    ServerResponse checkMemberName(String name);

    ServerResponse checkMemberPhone(String phone);


    ServerResponse register(Member member);

    Member getMemberByName(String name);

    Member findMemberById(Integer id);

    ServerResponse login(Member member, HttpServletResponse response);

    ServerResponse sendCode(String phone);

}
