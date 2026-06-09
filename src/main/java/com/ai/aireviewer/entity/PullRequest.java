package com.ai.aireviewer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "pull_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PullRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long githubPrId;

    private Integer prNumber;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String state;

    private String sourceBranch;

    private String targetBranch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_id")
    private RepositoryEntity repository;
}
