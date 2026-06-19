package com.ai.aireviewer.controller;

import com.ai.aireviewer.dto.ReviewResponseDTO;
import com.ai.aireviewer.review.LLMReviewAgent;
import com.ai.aireviewer.review.ReviewParser;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final LLMReviewAgent llmReviewAgent;
    private final ReviewParser reviewParser;

    @PostMapping("/review")
    public ReviewResponseDTO review(
            @RequestBody String patch) throws Exception {

        String response =
                llmReviewAgent.review(patch);

        return reviewParser.parse(response);
    }
}
