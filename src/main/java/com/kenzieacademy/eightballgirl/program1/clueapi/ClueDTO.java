package com.kenzieacademy.eightballgirl.program1.clueapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*~ ClueDTO is a Data Transfer Object.
            This will become an object that Quiz uses to find question/answer pairs. ~*/
public class ClueDTO {


    @JsonProperty("clues")
    private List<Clues> clues;

    public List<Clues> getClues() {
        return clues;
    }

    public void setClues(List<Clues> clues) {
        this.clues = clues;
    }
}



