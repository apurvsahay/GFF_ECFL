package com.example.gffecfl.Objects;

public class Teams {
    String team_name;
    String team_leader;
    String team_member;

    public Teams(){}

    public Teams(String team_name, String team_leader, String team_member) {
        this.team_name = team_name;
        this.team_leader = team_leader;
        this.team_member = team_member;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_leader() {
        return team_leader;
    }

    public void setTeam_leader(String team_leader) {
        this.team_leader = team_leader;
    }

    public String getTeam_member() {
        return team_member;
    }

    public void setTeam_member(String team_member) {
        this.team_member = team_member;
    }
}
