package com.arakoo.jsonfunction.dto;


public class UserRequest {
    
    public String prompt;
    public Object format;

    public UserRequest(String prompt, Object format) {
        this.prompt = prompt;
        this.format = format;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Object getFormat() {
        return format;
    }

    public void setFormat(Object format) {
        this.format = format;
    }

    

}
