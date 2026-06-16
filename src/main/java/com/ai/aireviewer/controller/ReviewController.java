package com.ai.aireviewer.controller;

import com.ai.aireviewer.dto.ReviewResponseDTO;
import com.ai.aireviewer.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{prId}")
    public ResponseEntity<ReviewResponseDTO> reviewPR(
            @PathVariable String prId) {

        return ResponseEntity.ok(
                reviewService.reviewPullRequest(prId));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> getReview(
            @PathVariable String reviewId) {

        return ResponseEntity.ok(
                reviewService.getReview(reviewId));
    }

    @PostMapping("/generate/{pullRequestId}")
    public ResponseEntity<String> generateReview(
            @PathVariable UUID pullRequestId) {

        UUID reviewId =
                reviewService.generateReview(
                        pullRequestId);

        return ResponseEntity.ok(
                reviewId.toString());
    }
}
