package com.ai.aireviewer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private Integer overallScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pull_request_id")
    private PullRequest pullRequest;
}
