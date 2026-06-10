CREATE TABLE changed_files (
                               id CHAR(36) PRIMARY KEY,

                               filename TEXT,

                               status VARCHAR(50),

                               additions INT,

                               deletions INT,

                               patch LONGTEXT,

                               pull_request_id CHAR(36),

                               created_at TIMESTAMP NOT NULL,

                               updated_at TIMESTAMP NULL,

                               CONSTRAINT fk_changed_file_pr
                                   FOREIGN KEY (pull_request_id)
                                       REFERENCES pull_requests(id)
);