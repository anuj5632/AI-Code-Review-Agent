CREATE TABLE review_comments (
                                 id CHAR(36) PRIMARY KEY,

                                 filename TEXT,

                                 line_number INT,

                                 severity VARCHAR(50),

                                 category VARCHAR(50),

                                 comment LONGTEXT,

                                 suggestion LONGTEXT,

                                 review_id CHAR(36),

                                 created_at TIMESTAMP NOT NULL,

                                 updated_at TIMESTAMP NULL,

                                 CONSTRAINT fk_comment_review
                                     FOREIGN KEY (review_id)
                                         REFERENCES reviews(id)
);