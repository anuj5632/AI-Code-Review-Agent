package com.ai.aireviewer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "changed_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangedFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String filename;

    private String status;

    private Integer additions;

    private Integer deletions;

    @Column(columnDefinition = "TEXT")
    private String patch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pull_request_id")
    private PullRequest pullRequest;
}