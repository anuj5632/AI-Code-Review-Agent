CREATE TABLE pull_requests (
                               id CHAR(36) PRIMARY KEY,

                               github_pr_id BIGINT UNIQUE,

                               pr_number INT,

                               title TEXT,

                               description TEXT,

                               state VARCHAR(50),

                               source_branch VARCHAR(100),

                               target_branch VARCHAR(100),

                               repository_id CHAR(36),

                               created_at TIMESTAMP NOT NULL,

                               updated_at TIMESTAMP NULL,

                               CONSTRAINT fk_pr_repository
                                   FOREIGN KEY (repository_id)
                                       REFERENCES repositories(id)
);