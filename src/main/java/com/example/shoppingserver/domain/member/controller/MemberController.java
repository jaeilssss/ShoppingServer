package com.example.shoppingserver.domain.member.controller;

import com.example.shoppingserver.domain.member.dao.DeleteMemberRepository;
import com.example.shoppingserver.domain.member.dto.LoginRequest;
import com.example.shoppingserver.domain.member.dto.LoginResponse;
import com.example.shoppingserver.domain.member.dto.MemberRequest;
import com.example.shoppingserver.domain.member.dto.MemberResponse;
import com.example.shoppingserver.domain.member.entity.DeleteMember;
import com.example.shoppingserver.domain.member.service.MemberService;
import com.example.shoppingserver.globals.Response;
import com.example.shoppingserver.globals.base.BaseController;
import com.example.shoppingserver.globals.http.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController extends BaseController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Response<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                memberService.login(loginRequest)
        );
    }

    @PostMapping("/signUp")
    public Response<Void> signUp(@RequestBody MemberRequest memberRequest) {
        memberService.signUp(memberRequest);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE
        );
    }

    @GetMapping("/{memberId}")
    public Response<MemberResponse> getMyMemberInfo(@PathVariable Long memberId) {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                memberService.getMyMemberInfo(memberId)
        );
    }

    @PutMapping("/{memberId}")
    public Response<MemberResponse> updateMemberInfo(@PathVariable Long memberId, @RequestBody MemberRequest memberRequest) {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                memberService.updateMemberInfo(memberId, memberRequest)
        );
    }

    @DeleteMapping("/{memberId}")
    public Response<Void> deleteMemberInfo(@PathVariable Long memberId) {
        memberService.deleteMemberInfo(memberId);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                null
        );
    }
}
