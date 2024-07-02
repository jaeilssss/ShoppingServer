package com.example.shoppingserver.domain.member.entity;

import com.example.shoppingserver.domain.member.dto.MemberRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private Address address;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Member createMember(MemberRequest memberRequest) {
        return Member.builder()
                .email(memberRequest.getEmail())
                .password(memberRequest.getPassword())
                .name(memberRequest.getName())
                .address(new Address(
                        memberRequest.getAddress().getCity(),
                        memberRequest.getAddress().getStreet(),
                        memberRequest.getAddress().getZipCode()))
                .build();
    }

    public void updateMember(MemberRequest memberRequest) {
        this.email = memberRequest.getEmail();
        this.password = memberRequest.getPassword();
        this.name = memberRequest.getName();
        this.address = memberRequest.getAddress();
    }
}
