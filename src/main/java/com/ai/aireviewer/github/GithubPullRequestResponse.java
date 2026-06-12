package com.ai.aireviewer.github;

import lombok.Data;

@Data
public class GithubPullRequestResponse {

    private Long id;

    private Integer number;

    private String title;

    private String state;

    private Head head;

    private Base base;

    @Data
    public static class Head {
        private String ref;
    }

    @Data
    public static class Base {
        private String ref;
    }
}
