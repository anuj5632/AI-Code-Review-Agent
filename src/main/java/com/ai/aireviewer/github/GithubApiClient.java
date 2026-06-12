package com.ai.aireviewer.github;

import com.ai.aireviewer.github.GithubChangedFileResponse;
import com.ai.aireviewer.github.GithubPullRequestResponse;
import com.ai.aireviewer.github.GithubRepositoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GithubApiClient {

    private final RestClient restClient;

    @Value("${github.api.base-url}")
    private String githubApiUrl;

    @Value("${github.api.token}")
    private String githubToken;

    private String bearer() {
        return "Bearer " + githubToken;
    }

    public List<GithubRepositoryResponse> getRepositories() {

        return restClient.get()
                .uri(githubApiUrl + "/user/repos")
                .header("Authorization", bearer())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<GithubPullRequestResponse> getPullRequests(
            String owner,
            String repo) {

        return restClient.get()
                .uri(githubApiUrl +
                        "/repos/" +
                        owner +
                        "/" +
                        repo +
                        "/pulls")
                .header("Authorization", bearer())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<GithubChangedFileResponse> getPullRequestFiles(
            String owner,
            String repo,
            Integer prNumber) {

        return restClient.get()
                .uri(githubApiUrl +
                        "/repos/" +
                        owner +
                        "/" +
                        repo +
                        "/pulls/" +
                        prNumber +
                        "/files")
                .header("Authorization", bearer())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    
}
