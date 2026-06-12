package com.ai.aireviewer.repository;

import com.ai.aireviewer.entity.PullRequest;
import com.ai.aireviewer.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PullRequestRepository extends JpaRepository<PullRequest, UUID> {
    Optional<PullRequest> findByGithubPrId(long githubPrId);
    List<PullRequest> findByRepositoryId(UUID repositoryId);
    boolean existsByGithubPrId(Long githubPrId);
}
