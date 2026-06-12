package com.ai.aireviewer.github;

import lombok.Data;

@Data
public class GithubRepositoryResponse {
    private Long id;

    private String name;

    private String full_name;

    private  Owner owner;

    private String default_branch;

    @Data
    public static class Owner{
        private String login;
    }
}
