package com.arakoo.jsonfunction.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arakoo.jsonfunction.dto.ChatGPTFunctionRequest;
import com.arakoo.jsonfunction.dto.ChatGPTRequest;
import com.arakoo.jsonfunction.dto.ChatGptResponse;
import com.arakoo.jsonfunction.dto.FunctionRequest;
import com.arakoo.jsonfunction.dto.Message;
import com.arakoo.jsonfunction.dto.MultiplePromptRequest;
import com.arakoo.jsonfunction.dto.Parameters;
import com.arakoo.jsonfunction.dto.UserRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class JsonContrroller {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @PostMapping("/chat")
    public Object chat(@RequestBody String prompt) {
        System.out.println(prompt);
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);

        String res = chatGptResponse.getChoices().get(0).getMessage().getContent();
        System.out.println(res);
        HashMap<String, String> map = new HashMap<>();
        map.put("response", res);
        return map;
    }

    @PostMapping("/userStory")
    public Object userStory(@RequestBody String prompt) {

        String rePrompt = """
                what ever the question asked you want to response in valid JSON format not any other text.
                For example if the question is "what is your name" then you want to response in JSON format like this
                {
                    "name": "John"
                }
                if the user ask particular JSON format then you want to response in that format.
                if unable to give you response in JSON format then you want to response in these format {"error": "unable to give AI response in JSON format"}
                The user question is : 
                """ + prompt ;
        
        System.out.println(rePrompt);
        ChatGPTRequest request = new ChatGPTRequest(model, rePrompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);

        if (chatGptResponse == null) {
            System.out.println("ChatGptResponse is null. There was an error processing the request.");
            return "Error: Unable to process the request.";
        }

        System.out.println(chatGptResponse);

        // Verify if the return message is a valid JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(chatGptResponse.getChoices().get(0).getMessage().getContent());
            System.out.println("The response is a valid JSON string." + jsonNode);
        } catch (Exception e) {
            System.out.println("The response is not a valid JSON string.");
        }

        String res = chatGptResponse.getChoices().get(0).getMessage().getContent();
        HashMap<String, String> map = new HashMap<>();
        map.put("response", res);
        return map;
    }

    @GetMapping("/extract")
    public String extract(@RequestBody UserRequest userRequest) {

        String rePrompt = "INPUT = " + userRequest.getPrompt() + " EXTRACTED = " + userRequest.getFormat()
                + " Return EXTRACTED as a valid JSON object.";
        System.out.println(rePrompt);
        ChatGPTRequest request = new ChatGPTRequest(model, rePrompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);

        if (chatGptResponse == null) {
            System.out.println("ChatGptResponse is null. There was an error processing the request.");
            return "Error: Unable to process the request.";
        }

        System.out.println(chatGptResponse);

        // Verify if the return message is a valid JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(chatGptResponse.getChoices().get(0).getMessage().getContent());
            System.out.println("The response is a valid JSON string." + jsonNode);
        } catch (Exception e) {
            System.out.println("The response is not a valid JSON string.");
        }

        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }

    @GetMapping("/function")
    public Object function(@RequestBody UserRequest userRequest) {

        Parameters parameters = new Parameters("object", userRequest.getFormat());
        FunctionRequest function = new FunctionRequest("reply_user", "reply to user's query", parameters);

        List<FunctionRequest> functions = new ArrayList<>();
        functions.add(function);

        Message message = new Message("system", "Only use function_call to reply to use. Do not use content.");
        Message message2 = new Message("user", userRequest.getPrompt());
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        messages.add(message2);

        ChatGPTFunctionRequest request = new ChatGPTFunctionRequest("gpt-3.5-turbo-0613", messages, functions);
        System.out.println(request);

        ChatGptResponse response = template.postForObject(apiURL, request, ChatGptResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            System.out.println("ChatGptResponse is null. There was an error processing the request.");
            return "Error: Unable to process the request.";
        }

        System.out.println(response.getChoices().get(0).getMessage().getFunction_call());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper
                    .readTree(response.getChoices().get(0).getMessage().getFunction_call().getArguments());
            System.out.println("The response is a valid JSON string." + jsonNode);
        } catch (Exception e) {
            System.out.println("The response is not a valid JSON string.");
        }

        return response.getChoices().get(0).getMessage().getFunction_call().getArguments();
    }

    @GetMapping("/situation")
    public String handleSituation(@RequestBody MultiplePromptRequest multiplePromptRequest) {

        String situation = multiplePromptRequest.getSituation();
        String validAction = multiplePromptRequest.getValidAction();
        String callToAction = multiplePromptRequest.getCallToAction();
        String actionFormat = multiplePromptRequest.getActionFormat();

        String getActionPrompt = "This is the situation: " + situation
                + " These are the set of valid actions to take: "
                + validAction + " " + callToAction;

        ChatGPTRequest request = new ChatGPTRequest(model, getActionPrompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        String response1 = chatGptResponse.getChoices().get(0).getMessage().getContent();
        System.out.println("response1: " + response1);

        String validActionCheckPrompt = "Given the situation: " + situation + " And the action you chose: " + response1
                + " Is the action you in this set of valid actions: " + validAction
                + "? If not, choose the best valid action to take. If so, please return the original action";

        ChatGPTRequest request2 = new ChatGPTRequest(model, validActionCheckPrompt);
        ChatGptResponse chatGptResponse2 = template.postForObject(apiURL, request2, ChatGptResponse.class);
        String response2 = chatGptResponse2.getChoices().get(0).getMessage().getContent();
        System.out.println("response2: " + response2);

        String getActionFormat = "This is the correct format for an action: " + actionFormat
                + " This is the chosen action: " + response2 + " Convert the chosen action to the correct format.";

        ChatGPTRequest request3 = new ChatGPTRequest(model, getActionFormat);
        ChatGptResponse chatGptResponse3 = template.postForObject(apiURL, request3, ChatGptResponse.class);
        String response3 = chatGptResponse3.getChoices().get(0).getMessage().getContent();
        System.out.println("response3: " + response3);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response3);
            System.out.println("The response is a valid JSON string." + jsonNode);
            return response3;
        } catch (Exception e) {
            System.out.println("The response is not a valid JSON string so retrying.");
            String getValidFormat = "This is the correct format for an action: " + actionFormat
                    + " This is a formatted action: " + response3 + " Return the action in the correct format.";
            ChatGPTRequest request4 = new ChatGPTRequest(model, getValidFormat);
            ChatGptResponse chatGptResponse4 = template.postForObject(apiURL, request4, ChatGptResponse.class);
            String response4 = chatGptResponse4.getChoices().get(0).getMessage().getContent();
            System.out.println("Final Response: " + response4);
            return response4;
        }
    }

}
