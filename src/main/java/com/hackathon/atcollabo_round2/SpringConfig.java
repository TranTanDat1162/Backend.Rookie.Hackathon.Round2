package com.hackathon.atcollabo_round2;

import com.hackathon.atcollabo_round2.repository.MemoryMemberRepository;
import com.hackathon.atcollabo_round2.service.MemberService;
import com.hackathon.atcollabo_round2.repository.MemberRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
