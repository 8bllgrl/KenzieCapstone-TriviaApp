package com.kenzieacademy.eightballgirl.program1.clueapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/*~ An extension of the ClueDTO Class. ~*/
public class Game {
    @JsonProperty("canon")
    private boolean canon;
    @JsonProperty("aired")
    private String aired;

    public boolean getCanon() {
        return canon;
    }

    public void setCanon(boolean canon) {
        this.canon = canon;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }
}
