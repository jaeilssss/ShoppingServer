package com.example.shoppingserver.domain.member.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberCodes {
    INCORRECT_MEMBER_INFO("MEMBER_001", "아이디 와 비밀번호 정보가 잘못되었습니다"),
    EXIST_MEMBER_EMAIL("MEMBER_002", "가입된 이메일이 있습니다."),
    NOT_EXIST_MEMBER_DATA("MEMBER_003", "멤버 정보가 존재 하지 않습니다.");

    private final String code;
    private final String message;


}
