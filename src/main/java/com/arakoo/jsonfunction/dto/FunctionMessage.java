package com.arakoo.jsonfunction.dto;

public class FunctionMessage {

    private Fucntion function_call;
    private String role;
    private String content;

    public static class Fucntion {
        private String name;
        private String arguments;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getArguments() {
            return arguments;
        }
        public void setArguments(String arguments) {
            this.arguments = arguments;
        }

    }

    public Fucntion getFunction_call() {
        return function_call;
    }

    public void setFunction_call(Fucntion function_call) {
        this.function_call = function_call;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
