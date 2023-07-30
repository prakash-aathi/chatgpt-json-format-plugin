package com.arakoo.jsonfunction.dto;

import java.util.List;

public class ChatGPTFunctionRequest {
    
    private String model;
    private List<Message> messages;
    private double temperature = 0.7;
    private List<FunctionRequest> functions;
    private String function_call = "auto";

    public ChatGPTFunctionRequest(String model, List<Message> messages, List<FunctionRequest> functions) {
        this.model = model;
        this.messages = messages;
        this.functions = functions;
    }

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

    public List<FunctionRequest> getFunctions() {
        return functions;
    }

    public void setFunctions(List<FunctionRequest> functions) {
        this.functions = functions;
    }

    public String getFunction_call() {
        return function_call;
    }

    public void setFunction_call(String function_call) {
        this.function_call = function_call;
    }

    
   
}
