package com.hackathon.atcollabo_round2.service;

import com.hackathon.atcollabo_round2.domain.Member;
import com.hackathon.atcollabo_round2.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.IterableAssert.assertThatIterable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;
    
    @BeforeEach // run before every @Test
    void beforeEach() {
        this.memoryMemberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(this.memoryMemberRepository);
    }
    
    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }
    
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("memberA");
        
        // when
        Long savedId = memberService.join(member);
        
        // then
        Member findMember = memoryMemberRepository.findById(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        
    }
    
    @Test
    void duplicateMemberException() {
        // given
        Member member1 = new Member();
        member1.setName("memberA");
        
        Member member2 = new Member();
        member2.setName("memberA");
        
        // when
        memberService.join(member1);
        
        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("memberA");

        Member member2 = new Member();
        member2.setName("memberB");

        memberService.join(member1);
        memberService.join(member2);

        // when
        List<Member> members = memberService.findMembers();

        // then
        assertAll(
                () -> assertThat(members).isNotNull(),
                () -> assertThat(members.size()).isEqualTo(2),
                () -> assertThatIterable(members.stream().map(Member::getName).collect(Collectors.toList()))
                        .containsOnly("memberA", "memberB")
        );
    }

    @Test
    void findOne() {
        // given
        Member member = new Member();
        member.setName("memberA");

        Long savedId = memberService.join(member);

        // when
        Optional<Member> foundMember = memberService.findOne(savedId);

        // then
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.get()).isEqualTo("memberA");
    }
}