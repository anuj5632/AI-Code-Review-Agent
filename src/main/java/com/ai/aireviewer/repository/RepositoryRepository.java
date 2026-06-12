package com.ai.aireviewer.repository;

import com.ai.aireviewer.entity.AIReviewLogs;
import com.ai.aireviewer.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryRepository extends JpaRepository<RepositoryEntity, UUID> {
    Optional<RepositoryEntity> findByGithubRepoId(String repoId);
    boolean existsByGithubRepoId(Long githubRepoId);
}
