package com.ai.aireviewer.service;

import com.ai.aireviewer.dto.ReviewResponseDTO;

public interface ReviewService {
    ReviewResponseDTO getReview(String id);
     ReviewResponseDTO reviewPullRequest(String prId);
}
