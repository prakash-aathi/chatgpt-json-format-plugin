package com.arakoo.jsonfunction.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTRequest {

    private String model;
    private List<Message> messages;
    private double temperature = 0.7;
    // private List<FunctionRequest> functions;
    // private String function_call ;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    // public ChatGPTRequest(String model, List<Message> messages, List<FunctionRequest> functions) {
    //     this.model = model;
    //     this.messages = messages;
    //     this.functions = functions;
    // }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

 
}
