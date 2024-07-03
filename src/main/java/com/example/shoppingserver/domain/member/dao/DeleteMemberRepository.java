package com.example.shoppingserver.domain.member.dao;

import com.example.shoppingserver.domain.member.entity.DeleteMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteMemberRepository extends JpaRepository<DeleteMember, Long> {
}
