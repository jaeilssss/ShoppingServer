package com.example.shoppingserver.domain.member.service;

import com.example.shoppingserver.domain.member.dao.DeleteMemberRepository;
import com.example.shoppingserver.domain.member.dao.MemberRepository;
import com.example.shoppingserver.domain.member.dto.MemberRequest;
import com.example.shoppingserver.domain.member.dto.MemberResponse;
import com.example.shoppingserver.domain.member.entity.Address;
import com.example.shoppingserver.domain.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class MemberServiceTest {

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private DeleteMemberRepository deleteMemberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 로직 테스트")
    public void signUpTest() throws Exception {
        //given
        MemberRequest memberRequest = createMemberRequest();
        when(memberRepository.save(any())).thenReturn(createMember());

        //when
        MemberResponse memberResponse = memberService.signUp(memberRequest);

        //then
        assertEquals(memberRequest.getEmail(), memberResponse.getEmail(), memberResponse.getEmail());
        assertEquals(memberRequest.getName(), memberResponse.getName(), memberResponse.getName());
        assertEquals(memberRequest.getAddress().getCity(), memberResponse.getAddress().getCity(), memberResponse.getAddress().getCity());
        assertEquals(memberRequest.getAddress().getStreet(), memberResponse.getAddress().getStreet(), memberResponse.getAddress().getStreet());
        assertEquals(memberRequest.getAddress().getZipCode(), memberResponse.getAddress().getZipCode(), memberResponse.getAddress().getZipCode());
    }

    public MemberRequest createMemberRequest() {
        return new MemberRequest(
                "test1@test.com",
                "1234",
                "test",
                new Address("seoul", "dorim", "12345")
        );
    }

    public Member createMember() {
        return new Member(
                22L,
                "test1@test.com",
                "1234",
                "test",
                new Address("seoul", "dorim", "12345"),
                null,
                null
        );
    }

    public MemberResponse createMemberResponse() {
        return new MemberResponse(
                22L,
                "test1@test.com",
                "test",
                new Address("seoul", "dorim", "12345")
        );
    }
}