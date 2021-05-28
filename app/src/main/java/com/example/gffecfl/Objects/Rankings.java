package com.example.gffecfl.Objects;

public class Rankings {
    String teamName;
    String leader;
    String member;
    int points;

    public Rankings(String teamName, String leader, String member, int points) {
        this.teamName = teamName;
        this.leader = leader;
        this.member = member;
        this.points = points;
    }

    public Rankings(){}

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
