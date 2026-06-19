package com.ai.aireviewer.review;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LLMReviewAgent {

    private final ChatClient chatClient;
    private final PromptBuilder promptBuilder;

    public String review(String patch) {

        String prompt =
                promptBuilder.buildReviewPrompt(patch);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
