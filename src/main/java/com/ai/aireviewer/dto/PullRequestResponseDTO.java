package com.ai.aireviewer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PullRequestResponseDTO {

    private String id;
    private Integer prNumber;
    private String title;
    private String state;
    private String sourceBranch;
    private String targetBranch;
}
