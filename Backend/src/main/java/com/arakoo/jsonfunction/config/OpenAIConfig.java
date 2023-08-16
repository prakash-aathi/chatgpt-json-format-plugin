package com.arakoo.jsonfunction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {

    // @Value("${openai.api.key}")
    // String openaiApiKey;
     
    // @Value("${openai.organization.id}") 
    // private String openaiOrganizationId;

    public static String apiKey;

    @Bean
    public RestTemplate template(){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            // request.getHeaders().add("OpenAI-Organization", openaiOrganizationId); 
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
