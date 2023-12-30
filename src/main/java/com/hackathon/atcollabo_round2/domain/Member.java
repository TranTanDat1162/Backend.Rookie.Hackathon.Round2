package com.hackathon.atcollabo_round2.domain;

public class Member {
    private long id;
    private String name;

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
