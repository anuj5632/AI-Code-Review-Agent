CREATE TABLE ai_review_logs (
                                id CHAR(36) PRIMARY KEY,

                                prompt LONGTEXT,

                                response LONGTEXT,

                                pull_request_id CHAR(36),

                                created_at TIMESTAMP NOT NULL,

                                updated_at TIMESTAMP NULL,

                                CONSTRAINT fk_log_pr
                                    FOREIGN KEY (pull_request_id)
                                        REFERENCES pull_requests(id)
);