package com.ai.aireviewer.review;

import com.ai.aireviewer.dto.ReviewResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class ReviewParser {
    private final ObjectMapper objectMapper;

    public ReviewResponseDTO parse(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ReviewResponseDTO.class);
    }
}
