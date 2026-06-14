package com.ai.aireviewer.review;

import com.ai.aireviewer.dto.ReviewCommentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceReviewAgent {

    public List<ReviewCommentDTO> review(String patch) {

        List<ReviewCommentDTO> comments = new ArrayList<>();

        if (patch.contains("SELECT *")) {

            comments.add(
                    ReviewCommentDTO.builder()
                            .category("PERFORMANCE")
                            .severity("MEDIUM")
                            .comment(
                                    "Avoid using SELECT *. Fetch only required columns."
                            )
                            .build()
            );
        }

        if (patch.contains(".stream().count()")) {

            comments.add(
                    ReviewCommentDTO.builder()
                            .category("PERFORMANCE")
                            .severity("LOW")
                            .comment(
                                    "Consider using collection.size() instead of stream().count() where possible."
                            )
                            .build()
            );
        }

        return comments;
    }
}