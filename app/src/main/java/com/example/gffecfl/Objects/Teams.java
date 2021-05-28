package com.example.gffecfl.Objects;

public class Teams {
    String Leader;
    String Member;
    String Name;

    public Teams(){}

    public Teams(String leader, String member, String name) {
        Leader = leader;
        Member = member;
        Name = name;
    }

    public String getLeader() {
        return Leader;
    }

    public void setLeader(String leader) {
        Leader = leader;
    }

    public String getMember() {
        return Member;
    }

    public void setMember(String member) {
        Member = member;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
