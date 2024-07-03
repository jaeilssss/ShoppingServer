package com.example.shoppingserver.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DeleteMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deleteMemberId;
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private Address address;

    public DeleteMember createDeleteMember(Member member) {
        return DeleteMember.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .address(member.getAddress())
                .build();
    }
}
