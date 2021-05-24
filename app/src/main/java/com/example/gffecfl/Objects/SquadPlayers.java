package com.example.gffecfl.Objects;

public class SquadPlayers {
    String name;
    String inStartingEleven;

    public SquadPlayers(String name, String inStartingEleven) {
        this.name = name;
        this.inStartingEleven = inStartingEleven;
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
}
