package com.arakoo.jsonfunction.dto;

import java.util.List;

public class ChatGptResponse {

    private List<Choice> choices;

    public ChatGptResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public ChatGptResponse() {
    }

    public static class Choice {

        private int index;
        private FunctionMessage message;
        
        public Choice(int index, FunctionMessage message) {
            this.index = index;
            this.message = message;
        }

        public Choice() {
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public FunctionMessage getMessage() {
            return message;
        }

        public void setMessage(FunctionMessage message) {
            this.message = message;
        }

        

    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }


}
