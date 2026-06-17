package com.ai.aireviewer.review;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {
    public String buildReviewPrompt(String patch){
        return """
                You are a Senior Software Engineer.
                Review the following git diff.
                
                Focus on:
                -Security
                -Performance
                -Readability
                -Bugs
                
                Return ONLY JSON.
                
                {
                "summary" : "...",
                "comments" : [
                    {   
                        "category" : "SECURITY",
                        "severity" : "HIGH",
                        "comment" : "..."
                    }
                  ]
                }
                Diff:
                %s
                """.format(patch);
    }
}
