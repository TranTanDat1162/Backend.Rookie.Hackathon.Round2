package com.hackathon.atcollabo_round2;

import com.hackathon.atcollabo_round2.repository.JpaMemberRepository;
import com.hackathon.atcollabo_round2.repository.MemoryMemberRepository;
import com.hackathon.atcollabo_round2.service.MemberService;
import com.hackathon.atcollabo_round2.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final EntityManager entityManager;

    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(entityManager);
    }
}
