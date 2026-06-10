CREATE TABLE reviews (
                         id CHAR(36) PRIMARY KEY,

                         summary LONGTEXT,

                         overall_score INT,

                         pull_request_id CHAR(36),

                         created_at TIMESTAMP NOT NULL,

                         updated_at TIMESTAMP NULL,

                         CONSTRAINT fk_review_pr
                             FOREIGN KEY (pull_request_id)
                                 REFERENCES pull_requests(id)
);