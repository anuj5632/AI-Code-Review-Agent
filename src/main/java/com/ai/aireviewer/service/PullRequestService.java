package com.ai.aireviewer.service;

import com.ai.aireviewer.dto.PullRequestResponseDTO;

public interface PullRequestService {
    PullRequestResponseDTO getPullRequest(String id);

}
