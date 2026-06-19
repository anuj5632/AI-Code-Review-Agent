package com.ai.aireviewer.review;

import com.ai.aireviewer.dto.ReviewResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewParser {

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    public ReviewResponseDTO parse(String response)
            throws Exception {

        String cleanedResponse = response
                .replace("```json", "")
                .replace("```", "")
                .trim();

        return objectMapper.readValue(
                cleanedResponse,
                ReviewResponseDTO.class
        );
    }
}