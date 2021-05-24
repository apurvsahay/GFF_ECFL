package com.example.gffecfl.Objects;

public class SquadPlayers {
    String name;
    String inStartingEleven;
    String points1;
    String points2;
    String points3;

    public SquadPlayers(String name, String inStartingEleven, String points1, String points2, String points3) {
        this.name = name;
        this.inStartingEleven = inStartingEleven;
        this.points1 = points1;
        this.points2 = points2;
        this.points3 = points3;
    }

    public SquadPlayers(String name, String inStartingEleven){
        this.name = name;
        this.inStartingEleven = inStartingEleven;
        this.points1 = "0";
        this.points2 = "0";
        this.points3 = "0";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInStartingEleven() {
        return inStartingEleven;
    }

    public void setInStartingEleven(String inStartingEleven) {
        this.inStartingEleven = inStartingEleven;
    }

    public String getPoints1() {
        return points1;
    }

    public void setPoints1(String points1) {
        this.points1 = points1;
    }

    public String getPoints2() {
        return points2;
    }

    public void setPoints2(String points2) {
        this.points2 = points2;
    }

    public String getPoints3() {
        return points3;
    }

    public void setPoints3(String points3) {
        this.points3 = points3;
    }
}
