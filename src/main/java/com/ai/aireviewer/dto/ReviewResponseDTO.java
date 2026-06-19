package com.ai.aireviewer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewResponseDTO {

    private String reviewId;
    private Integer score;
    private String summary;
    private List<ReviewCommentDTO> comments;
}
