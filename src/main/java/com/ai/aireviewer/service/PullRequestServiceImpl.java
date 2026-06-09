package com.ai.aireviewer.service;

import com.ai.aireviewer.dto.PullRequestResponseDTO;
import com.ai.aireviewer.entity.PullRequest;
import com.ai.aireviewer.repository.PullRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PullRequestServiceImpl implements PullRequestService {

    private final PullRequestRepository pullRequestRepository;

    public PullRequestResponseDTO getPullRequest(String id){
        PullRequest pr = pullRequestRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Pull request not found"));
        return PullRequestResponseDTO.builder()
                .id(pr.getId().toString())
                .prNumber(pr.getPrNumber())
                .title(pr.getTitle())
                .state(pr.getState())
                .sourceBranch(pr.getSourceBranch())
                .targetBranch(pr.getTargetBranch())
                .build();
    }
}
