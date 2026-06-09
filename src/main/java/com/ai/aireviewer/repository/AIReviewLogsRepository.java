package com.ai.aireviewer.repository;


import com.ai.aireviewer.entity.AIReviewLogs;
import com.ai.aireviewer.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AIReviewLogsRepository extends JpaRepository<AIReviewLogs, UUID> {

}
