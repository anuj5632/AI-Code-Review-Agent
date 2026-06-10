CREATE TABLE repositories (
                              id CHAR(36) PRIMARY KEY,

                              github_repo_id BIGINT UNIQUE,

                              name VARCHAR(255),

                              owner VARCHAR(255),

                              full_name VARCHAR(255),

                              default_branch VARCHAR(100),

                              user_id CHAR(36),

                              created_at TIMESTAMP NOT NULL,

                              updated_at TIMESTAMP NULL,

                              CONSTRAINT fk_repository_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(id)
);