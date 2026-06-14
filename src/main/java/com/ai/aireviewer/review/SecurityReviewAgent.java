package com.ai.aireviewer.review;

import com.ai.aireviewer.dto.ReviewCommentDTO;
import com.ai.aireviewer.entity.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityReviewAgent {

    public List<ReviewCommentDTO> review(String patch) {
        List<ReviewCommentDTO> comments = new ArrayList<>();

        if(patch.contains("password.equals")){
            comments.add(
                    ReviewCommentDTO.builder()
                            .category("Security")
                            .severity("High")
                            .comment("Avoid comparing passwords using equals()")
                            .build()
            );
        }
        return comments;
    }
}
