package com.ai.aireviewer.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SyncResponseDTO {

    private Integer totalFetched;
    private Integer totalSaved;
    private String message;
}
