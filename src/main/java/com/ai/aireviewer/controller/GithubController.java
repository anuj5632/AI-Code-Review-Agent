package com.ai.aireviewer.controller;

import com.ai.aireviewer.dto.ReviewCommentDTO;
import com.ai.aireviewer.dto.SyncResponseDTO;
import com.ai.aireviewer.entity.ChangedFile;
import com.ai.aireviewer.github.GithubApiClient;
import com.ai.aireviewer.repository.ChangedFileRepository;
import com.ai.aireviewer.repository.RepositoryRepository;
import com.ai.aireviewer.repository.ReviewCommentRepository;
import com.ai.aireviewer.review.ReviewOrchestrator;
import com.ai.aireviewer.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubApiClient githubApiClient;
    private final RepositoryRepository repositoryRepository;
    private final GithubService githubService;
    private final ChangedFileRepository changedFileRepository;
    private final ReviewOrchestrator reviewOrchestrator;

    @GetMapping("/repos")
    public Object repos() {
        return githubApiClient.getRepositories();
    }

    @PostMapping("/sync/repos")
    public ResponseEntity<SyncResponseDTO> syncRepositories() {

        return ResponseEntity.ok(
                githubService.syncRepositories()
        );
    }
    @PostMapping("/sync/prs/{repositoryId}")
    public ResponseEntity<SyncResponseDTO> syncPullRequests(
            @PathVariable UUID repositoryId) {

        return ResponseEntity.ok(
                githubService.syncPullRequests(repositoryId)
        );
    }

    @PostMapping("/sync/files/{pullRequestId}")
    public ResponseEntity<SyncResponseDTO> syncChangedFiles(
            @PathVariable UUID pullRequestId) {

        return ResponseEntity.ok(
                githubService.syncChangedFiles(
                        pullRequestId
                )
        );
    }

    @PostMapping("/review/{changedFileId}")
    public List<ReviewCommentDTO> review(
            @PathVariable UUID changedFileId) {

        ChangedFile file =
                changedFileRepository.findById(
                                changedFileId)
                        .orElseThrow();

        return reviewOrchestrator.review(
                file.getPatch(),
                true
        );
    }

}
