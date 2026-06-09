package com.ai.aireviewer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCommentDTO {

    private String fileName;
    private Integer lineNumber;
    private String severity;
    private String category;
    private String comment;
    private String suggestion;
}