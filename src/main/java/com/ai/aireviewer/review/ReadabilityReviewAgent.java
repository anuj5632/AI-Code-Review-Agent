package com.ai.aireviewer.review;

import com.ai.aireviewer.dto.ReviewCommentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadabilityReviewAgent {

    public List<ReviewCommentDTO> review(String patch) {

        List<ReviewCommentDTO> comments = new ArrayList<>();

        if (patch.contains("System.out.println")) {

            comments.add(
                    ReviewCommentDTO.builder()
                            .category("READABILITY")
                            .severity("LOW")
                            .comment(
                                    "Replace System.out.println with a logger."
                            )
                            .build()
            );
        }

        if (patch.length() > 3000) {
            comments.add(
                    ReviewCommentDTO.builder()
                            .category("READABILITY")
                            .severity("MEDIUM")
                            .comment(
                                    "Large code change detected. Consider splitting into smaller commits."
                            )
                            .build()
            );
        }
        return comments;
    }
}
