package com.ai.aireviewer.review;

import com.ai.aireviewer.dto.ReviewCommentDTO;
import com.ai.aireviewer.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewOrchestrator {
    private final SecurityReviewAgent securityReviewAgent;
    private final PerformanceReviewAgent performanceReviewAgent;
    private final ReadabilityReviewAgent readabilityReviewAgent;
    private final PromptBuilder promptBuilder;
    private final LLMReviewAgent llmReviewAgent;
    private final ReviewParser reviewParser;

    public List<ReviewCommentDTO> review(String patch,boolean useAI){
        List<ReviewCommentDTO> comments = new ArrayList<>();

        if(useAI == true){
            String prompt = promptBuilder.buildReviewPrompt(patch);
            String reviewResponse = llmReviewAgent.review(prompt);
            try {
                ReviewResponseDTO reviewResponseDTO = reviewParser.parse(reviewResponse);
                comments.addAll(reviewResponseDTO.getComments());
            } catch (Exception e) {
                // Handle parsing exception
                e.printStackTrace();
            }
            return comments;
        }
        else {
            comments.addAll(securityReviewAgent.review(patch));
            comments.addAll(performanceReviewAgent.review(patch));
            comments.addAll(readabilityReviewAgent.review(patch));
            return comments;
        }
    }

}
