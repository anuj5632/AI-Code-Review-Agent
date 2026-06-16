package com.ai.aireviewer.service;

import com.ai.aireviewer.dto.ReviewCommentDTO;
import com.ai.aireviewer.dto.ReviewResponseDTO;

import java.util.UUID;

public interface ReviewService {
    ReviewResponseDTO getReview(String id);
    ReviewResponseDTO reviewPullRequest(String prId);
    UUID generateReview(UUID pullRequestId);
}
