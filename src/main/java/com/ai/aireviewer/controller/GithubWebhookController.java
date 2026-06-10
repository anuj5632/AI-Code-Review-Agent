package com.ai.aireviewer.controller;

import com.ai.aireviewer.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GithubWebhookController {

    private final GithubService githubService;

    @PostMapping("/webhook")
    public ResponseEntity<String> webhook(
            @RequestBody Map<String,Object> payload) {

        githubService.processWebhook(payload);

        return ResponseEntity.ok("Webhook received");
    }
}
