CREATE TABLE users (
                       id CHAR(36) PRIMARY KEY,

                       github_id BIGINT UNIQUE,

                       username VARCHAR(100),

                       email VARCHAR(255),

                       avatar_url TEXT,

                       access_token TEXT,

                       created_at TIMESTAMP NOT NULL,

                       updated_at TIMESTAMP NULL
);