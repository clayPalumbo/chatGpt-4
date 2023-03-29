package com.example.claySlalom.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class ChatGptService {

    public JsonNode askQuestion(JsonNode data) throws JsonProcessingException {
        HttpHeaders headers = getHeaders();
        String payload = createPayload(data.get("message").toString());
        return fetchChatGpt(payload, headers);

    }

    private JsonNode fetchChatGpt(String payload, HttpHeaders headers) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(payload, headers);
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String response = restTemplate.postForObject(apiUrl, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseMap =  mapper.readTree(response).get("choices");
        return responseMap;
    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String authToken = System.getenv("AUTH_TOKEN");
        headers.add("Authorization", "Bearer " + authToken);

        return headers;
    }
    // TODO: Fix this by turning it into a Map and then stringify
    private String createPayload(String data) {
        return "{" +
                "\"model\": \"gpt-4\"," +
                "\"max_tokens\": 100," +
                "\"messages\": [{" +
                "\"role\": \"user\"," +
                "\"content\":" + data +
                "}]" +
                "}";
    }
}
