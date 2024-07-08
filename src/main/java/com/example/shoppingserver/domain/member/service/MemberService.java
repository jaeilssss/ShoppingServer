package com.example.shoppingserver.domain.member.service;

import com.example.shoppingserver.domain.member.enums.MemberErrorCodes;
import com.example.shoppingserver.domain.member.dao.DeleteMemberRepository;
import com.example.shoppingserver.domain.member.dao.MemberRepository;
import com.example.shoppingserver.domain.member.dto.LoginRequest;
import com.example.shoppingserver.domain.member.dto.LoginResponse;
import com.example.shoppingserver.domain.member.dto.MemberRequest;
import com.example.shoppingserver.domain.member.dto.MemberResponse;
import com.example.shoppingserver.domain.member.entity.DeleteMember;
import com.example.shoppingserver.domain.member.entity.Member;
import com.example.shoppingserver.globals.exception.MyException;
import com.example.shoppingserver.globals.jwt.JwtProviders;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final DeleteMemberRepository deleteMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProviders jwtProviders;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new MyException(
                        MemberErrorCodes.INCORRECT_MEMBER_INFO.getCode(),
                        MemberErrorCodes.INCORRECT_MEMBER_INFO.getMessage()));

        if(isMatchPassword(loginRequest.getPassword(), member.getPassword())) {
            System.out.println("test successful");
            return new LoginResponse(jwtProviders.createToken(loginRequest.getEmail(), member.getMemberId()));
        } else {
            System.out.println("test error");

            throw new MyException(
                    MemberErrorCodes.INCORRECT_MEMBER_INFO.getCode(),
                    MemberErrorCodes.INCORRECT_MEMBER_INFO.getMessage());
        }
    }

    @Transactional
    public MemberResponse signUp(MemberRequest memberRequest) {
        isExistEmail(memberRequest.getEmail());
        memberRequest.setPassword(passwordEncoder.encode(memberRequest.getPassword()));
        return MemberResponse.createMemberResponse(memberRepository.save(Member.createMember(memberRequest)));
    }

    @Transactional
    public MemberResponse getMyMemberInfo(Long memberId) {
        return MemberResponse.createMemberResponse(getMember(memberId));
    }

    @Transactional
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> {
                    throw new MyException(
                            MemberErrorCodes.NOT_EXIST_MEMBER_DATA.getCode(),
                            MemberErrorCodes.NOT_EXIST_MEMBER_DATA.getMessage()
                    );
                });
    }

    @Transactional
    public MemberResponse updateMemberInfo(Long memberId, MemberRequest memberRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> {throw new MyException(
                        MemberErrorCodes.NOT_EXIST_MEMBER_DATA.getCode(),
                        MemberErrorCodes.NOT_EXIST_MEMBER_DATA.getMessage());
                });

        member.updateMember(memberRequest);
        return MemberResponse.createMemberResponse(member);
    }

    @Transactional
    public void isExistEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new MyException(
                            MemberErrorCodes.EXIST_MEMBER_EMAIL.getCode(),
                            MemberErrorCodes.EXIST_MEMBER_EMAIL.getMessage());
                });
    }

    public boolean isMatchPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    @Transactional
    public void deleteMemberInfo(Long memberId) {
        Member member = getMember(memberId);
        deleteMemberRepository.save(new DeleteMember().createDeleteMember(member));
        memberRepository.delete(member);
    }
}
