package com.example.chatGpt;
import com.example.chatGpt.services.ChatGptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatGptController {
    @PostMapping("/chat/gpt/v4")
    public ResponseEntity<Object> postData(@RequestBody JsonNode data) throws JsonProcessingException {
        try {
            ChatGptService chatGptService = new ChatGptService();
            return  new ResponseEntity<>(chatGptService.askQuestion(data), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<>(new Error("Your request failed at this time"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}