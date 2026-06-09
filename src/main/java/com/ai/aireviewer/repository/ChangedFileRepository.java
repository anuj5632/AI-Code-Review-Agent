package com.ai.aireviewer.repository;


import com.ai.aireviewer.entity.ChangedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChangedFileRepository extends JpaRepository<ChangedFile, UUID> {
    List<ChangedFile> findByPullRequestId(UUID pullRequestId);
}
