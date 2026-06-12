package com.ai.aireviewer.service;

import com.ai.aireviewer.dto.SyncResponseDTO;
import com.ai.aireviewer.entity.ChangedFile;
import com.ai.aireviewer.entity.PullRequest;
import com.ai.aireviewer.entity.RepositoryEntity;
import com.ai.aireviewer.exception.ResourceNotFoundException;
import com.ai.aireviewer.github.GithubApiClient;
import com.ai.aireviewer.github.GithubChangedFileResponse;
import com.ai.aireviewer.github.GithubPullRequestResponse;
import com.ai.aireviewer.github.GithubRepositoryResponse;
import com.ai.aireviewer.repository.ChangedFileRepository;
import com.ai.aireviewer.repository.PullRequestRepository;
import com.ai.aireviewer.repository.RepositoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

    private final GithubApiClient githubApiClient;
    private final RepositoryRepository repositoryRepository;
    private final PullRequestRepository pullRequestRepository;
    private final ChangedFileRepository changedFileRepository;

    public void processWebhook(Map<String, Object> payload) {
        log.info("Received GitHub webhook with payload: {}", payload);

        String action = (String) payload.get("action");

        log.info("Received GitHub webhook with action: {}", action);
    }

    @Override
    public SyncResponseDTO syncRepositories() {

        List<GithubRepositoryResponse> repos =
                githubApiClient.getRepositories();

        int saved = 0;

        for (GithubRepositoryResponse repo : repos) {

            boolean exists =
                    repositoryRepository
                            .existsByGithubRepoId(repo.getId());

            if (exists) {
                continue;
            }

            RepositoryEntity entity =
                    RepositoryEntity.builder()
                            .githubRepoId(repo.getId())
                            .name(repo.getName())
                            .owner(repo.getOwner().getLogin())
                            .fullName(repo.getFull_name())
                            .defaultBranch(repo.getDefault_branch())
                            .build();

            repositoryRepository.save(entity);

            saved++;
        }

        return SyncResponseDTO.builder()
                .totalFetched(repos.size())
                .totalSaved(saved)
                .message("Repositories synced successfully")
                .build();
    }

    @Override
    public SyncResponseDTO syncPullRequests(UUID repositoryId) {

        RepositoryEntity repository =
                repositoryRepository.findById(repositoryId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Repository not found with ID: " + repositoryId));

        List<GithubPullRequestResponse> prs =
                githubApiClient.getPullRequests(
                        repository.getOwner(),
                        repository.getName());

        int saved = 0;

        for (GithubPullRequestResponse pr : prs) {

            if (pullRequestRepository
                    .existsByGithubPrId(pr.getId())) {
                continue;
            }

            PullRequest entity =
                    PullRequest.builder()
                            .githubPrId(pr.getId())
                            .prNumber(pr.getNumber())
                            .title(pr.getTitle())
                            .state(pr.getState())
                            .sourceBranch(pr.getHead().getRef())
                            .targetBranch(pr.getBase().getRef())
                            .repository(repository)
                            .build();

            pullRequestRepository.save(entity);

            saved++;
        }

        return SyncResponseDTO.builder()
                .totalFetched(prs.size())
                .totalSaved(saved)
                .message("Pull requests synced successfully")
                .build();
    }
    @Override
    public SyncResponseDTO syncChangedFiles(UUID pullRequestId) {

        PullRequest pullRequest =
                pullRequestRepository.findById(pullRequestId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pull Request not found"));

        RepositoryEntity repository =
                pullRequest.getRepository();

        List<GithubChangedFileResponse> files =
                githubApiClient.getPullRequestFiles(
                        repository.getOwner(),
                        repository.getName(),
                        pullRequest.getPrNumber()
                );

        int saved = 0;

        for (GithubChangedFileResponse file : files) {

            boolean exists =
                    changedFileRepository
                            .existsByFilenameAndPullRequestId(
                                    file.getFilename(),
                                    pullRequestId
                            );

            if (exists) {
                continue;
            }

            ChangedFile entity =
                    ChangedFile.builder()
                            .filename(file.getFilename())
                            .status(file.getStatus())
                            .additions(file.getAdditions())
                            .deletions(file.getDeletions())
                            .patch(file.getPatch())
                            .pullRequest(pullRequest)
                            .build();

            changedFileRepository.save(entity);

            saved++;
        }

        return SyncResponseDTO.builder()
                .totalFetched(files.size())
                .totalSaved(saved)
                .message("Changed files synced successfully")
                .build();
    }
}
