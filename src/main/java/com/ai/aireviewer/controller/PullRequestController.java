package com.ai.aireviewer.controller;

import com.ai.aireviewer.dto.PullRequestResponseDTO;
import com.ai.aireviewer.service.PullRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prs")
@RequiredArgsConstructor
public class PullRequestController {

    private final PullRequestService pullRequestService;

    @GetMapping("/{id}")
    public ResponseEntity<PullRequestResponseDTO> getPR(
            @PathVariable String id) {

        return ResponseEntity.ok(
                pullRequestService.getPullRequest(id));
    }
}
