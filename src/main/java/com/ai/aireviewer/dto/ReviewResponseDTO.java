package com.ai.aireviewer.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDTO {

    private String reviewId;
    private Integer score;
    private String summary;
    private List<ReviewCommentDTO> comments;
}
