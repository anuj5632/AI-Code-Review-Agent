package com.ai.aireviewer.review;

import com.ai.aireviewer.dto.ReviewCommentDTO;
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

    public List<ReviewCommentDTO> review(String patch){
        List<ReviewCommentDTO> comments = new ArrayList<>();

        comments.addAll(securityReviewAgent.review(patch));
        comments.addAll(performanceReviewAgent.review(patch));
        comments.addAll(readabilityReviewAgent.review(patch));
        return comments;
    }

}
