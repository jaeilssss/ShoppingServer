package com.example.shoppingserver.domain.member.controller;

import com.example.shoppingserver.domain.member.dto.LoginRequest;
import com.example.shoppingserver.domain.member.dto.MemberRequest;
import com.example.shoppingserver.domain.member.dto.MemberResponse;
import com.example.shoppingserver.domain.member.dto.TokenResponse;
import com.example.shoppingserver.domain.member.entity.Address;
import com.example.shoppingserver.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Test
    @DisplayName("login test")
    void loginTest() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest("test@test.com", "1234");
        TokenResponse tokenResponse = new TokenResponse("someToken","someToken");
        String content = objectMapper.writeValueAsString(loginRequest);

        //when
        when(memberService.login(any(LoginRequest.class))).thenReturn(tokenResponse);

        // then
        mockMvc.perform(post("/api/member/login")
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andDo(print());
    }


    @Test
    @DisplayName("login fail test")
    void loginTestFail() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest("test111@test.com", "1234");
        TokenResponse tokenResponse = new TokenResponse("someToken","someToken");
        String content = objectMapper.writeValueAsString(loginRequest);

//        //when
//        when(memberService.login(any(LoginRequest.class))).thenReturn();

        // then
        mockMvc.perform(post("/api/member/login")
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    @DisplayName("signUp test")
    void signUpTest() throws Exception {
        // given
        MemberRequest memberRequest = new MemberRequest("test2@test.com", "1234","testUser",new Address("paju","금릉","12111"));
        String content = objectMapper.writeValueAsString(memberRequest);

        // then
        mockMvc.perform(post("/api/member/signUp")
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @DisplayName("getMyMemberInfo test")
    void getMyMemberInfoTest() throws Exception {
        // given
        Long memberId = 1L;
        MemberResponse memberResponse = new MemberResponse(memberId, "test1@test.com", "testUser",new Address("seoul","dorim","12121"));
        when(memberService.getMyMemberInfo(memberId)).thenReturn(memberResponse);

        // then
        mockMvc.perform(get("/api/member/{memberId}", memberId)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @DisplayName("updateMemberInfo test")
    void updateMemberInfoTest() throws Exception {
        // given
        Long memberId = 1L;
        MemberRequest memberRequest = new MemberRequest("test1@test.com", "1234", "updatedUser", new Address("seoul","dorim","12121"));
        MemberResponse memberResponse = new MemberResponse(memberId, "test1@test.com", "updatedUser", new Address("seoul","dorim","12121"));
        String content = objectMapper.writeValueAsString(memberRequest);

        when(memberService.updateMemberInfo(any(Long.class), any(MemberRequest.class))).thenReturn(memberResponse);

        // then
        mockMvc.perform(put("/api/member/{memberId}", memberId)
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @DisplayName("deleteMemberInfo test")
    void deleteMemberInfoTest() throws Exception {
        // given
        Long memberId = 1L;

        // then
        mockMvc.perform(delete("/api/member/{memberId}", memberId)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andDo(print());
    }
}