package com.kenzieacademy.eightballgirl.program2.logic.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClueDTO {

    @JsonProperty("canon")
    private boolean canon;
    @JsonProperty("game")
    private Game game;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("invalidCount")
    private int invalidCount;
    @JsonProperty("gameId")
    private int gameId;
    @JsonProperty("categoryId")
    private int categoryId;
    @JsonProperty("value")
    private int value;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answer")
    private String answer;
    @JsonProperty("id")
    private int id;

    public boolean getCanon() {
        return canon;
    }

    public Game getGame() {
        return game;
    }

    public Category getCategory() {
        return category;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public int getGameId() {
        return gameId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getValue() {
        return value;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public static class Game {
        @JsonProperty("canon")
        private boolean canon;
        @JsonProperty("aired")
        private String aired;

        public boolean getCanon() {
            return canon;
        }

        public String getAired() {
            return aired;
        }
    }

    public static class Category {
        @JsonProperty("canon")
        private boolean canon;
        @JsonProperty("title")
        private String title;
        @JsonProperty("id")
        private int id;

        public boolean getCanon() {
            return canon;
        }

        public String getTitle() {
            return title;
        }

        public int getId() {
            return id;
        }
    }
}
