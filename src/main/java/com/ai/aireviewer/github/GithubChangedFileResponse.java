package com.ai.aireviewer.github;


import lombok.Data;

@Data
public class GithubChangedFileResponse {

    private String filename;

    private String status;

    private Integer additions;

    private Integer deletions;

    private String patch;
}
