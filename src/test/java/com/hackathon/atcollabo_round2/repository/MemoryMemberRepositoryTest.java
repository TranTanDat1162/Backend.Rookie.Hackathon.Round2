package com.hackathon.atcollabo_round2.repository;

import com.hackathon.atcollabo_round2.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    // clear store for every @Test methods
    // to keep store empty, to make testing correctly
    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        // given:
        Member member = new Member();
        member.setName("Tấn Đạt!");
        
        // when:
        memoryMemberRepository.save(member);

        // then:
        Member result = memoryMemberRepository.findById(member.getId()).get();
        
        Assertions.assertThat(member).isEqualTo(result);
        
    }
    
    @Test
    public void findByName() {
        // given
        // save 2 member
        Member member1 = new Member();
        member1.setName("Member A");
        memoryMemberRepository.save(member1);
        
        Member member2 = new Member();
        member2.setName("Member B");
        memoryMemberRepository.save(member2);
        
        // when
        // findByName
        Member result = memoryMemberRepository.findByName("Member A").get();
        
        
        // then
        // true member == find member
        // the result must be equal to initial
        Assertions.assertThat(result).isEqualTo(member1);
    }
    
    @Test
    public void findByAll() {
        // given
        // save 2 member
        Member member1 = new Member();
        member1.setName("Member A");
        memoryMemberRepository.save(member1);
        
        Member member2 = new Member();
        member2.setName("Member B");
        memoryMemberRepository.save(member2);
        
        // when
        // findAll
        List<Member> result = memoryMemberRepository.findAll();
        
        // then
        // count of members == 2
        Assertions.assertThat(result.size()).isEqualTo(2);
        
    }
}
