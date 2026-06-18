package com.ai.aireviewer.review;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LLMReviewAgent {
    private final ChatClient chatClient;

    public String review(String prompt){
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
