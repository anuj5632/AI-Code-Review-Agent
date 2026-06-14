package com.ai.aireviewer.service;

import com.ai.aireviewer.dto.ReviewCommentDTO;
import com.ai.aireviewer.dto.ReviewResponseDTO;
import com.ai.aireviewer.entity.PullRequest;
import com.ai.aireviewer.entity.Review;
import com.ai.aireviewer.repository.PullRequestRepository;
import com.ai.aireviewer.repository.ReviewCommentRepository;
import com.ai.aireviewer.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository reviewCommentRepository;
    private final PullRequestRepository pullRequestRepository;

    @Override
    public ReviewResponseDTO getReview(String reviewId){
        Review review =
                reviewRepository.findById(UUID.fromString(reviewId))
                        .orElseThrow(() ->
                                new RuntimeException("Review not found"));

        List<ReviewCommentDTO> comments =
                reviewCommentRepository
                        .findByReviewId(review.getId())
                        .stream()
                        .map(comment ->
                                ReviewCommentDTO.builder()
                                        //.fileName(comment.getFilename())
                                        //.lineNumber(comment.getLineNumber())
                                        .severity(comment.getSeverity())
                                        .category(comment.getCategory())
                                        .comment(comment.getComment())
                                        //.suggestion(comment.getSuggestion())
                                        .build()
                        )
                        .toList();

        return ReviewResponseDTO.builder()
                .reviewId(review.getId().toString())
                .score(review.getOverallScore())
                .summary(review.getSummary())
                .comments(comments)
                .build();
    }

    @Override
    public ReviewResponseDTO reviewPullRequest(String prId){
        PullRequest pullRequest =
                pullRequestRepository.findById(UUID.fromString(prId))
                        .orElseThrow(() ->
                                new RuntimeException("PR not found"));

        Review review = Review.builder()
                .summary("AI Review Placeholder")
                .overallScore(85)
                .pullRequest(pullRequest)
                .build();

        reviewRepository.save(review);

        return ReviewResponseDTO.builder()
                .reviewId(review.getId().toString())
                .score(review.getOverallScore())
                .summary(review.getSummary())
                .comments(List.of())
                .build();
    }
}
