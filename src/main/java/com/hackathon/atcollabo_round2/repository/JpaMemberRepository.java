package com.hackathon.atcollabo_round2.repository;

import com.hackathon.atcollabo_round2.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    
    private final EntityManager entityManager;
    
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }
    
    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    
    @Override
    public Optional<Member> findByName(String name) {
        // SQL => JPQL
        List<Member> result = entityManager
                .createQuery("SELECT m " +
                                    "FROM Member m " +
                                    "WHERE m.name = :username", Member.class)
                .setParameter("username", name)
                .getResultList();
        return result.stream().findAny();
    }
    
    @Override
    public List<Member> findAll() {
        return entityManager
                .createQuery("SELECT m " +
                                    "FROM Member m", Member.class)
                .getResultList();
    }
}
