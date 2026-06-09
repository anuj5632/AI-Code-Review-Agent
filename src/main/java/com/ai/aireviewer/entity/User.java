package com.ai.aireviewer.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private Long githubId;

    private String username;

    private String email;

    private String avatarUrl;

    @Column(columnDefinition = "TEXT")
    private String accessToken;
}
