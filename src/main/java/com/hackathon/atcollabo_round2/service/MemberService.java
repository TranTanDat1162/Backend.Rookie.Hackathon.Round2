package com.hackathon.atcollabo_round2.service;

import com.hackathon.atcollabo_round2.domain.Member;
import com.hackathon.atcollabo_round2.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // for all
public class MemberService {
    private final MemberRepository memberRepository;
    
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    //@Transactional // for specified

    /**
     *
     * @param member
     * Member join (using for add user in table)
     * @return member.getId()
     */
    public Long join(Member member) {
        // check duplicate name user
        validateDuplicateMember(member);
        
        // if not duplication, saving member
        memberRepository.save(member);
        
        // return the id of member
        return member.getId();
    }
    
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("This member already exists.");
        });
    }

    /**
     *
     Retrieve All members
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     *
     * @param memberId
     * Find the id of member
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
