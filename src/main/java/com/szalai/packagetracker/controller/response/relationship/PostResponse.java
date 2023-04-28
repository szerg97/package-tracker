package com.szalai.packagetracker.controller.response.relationship;

import java.time.LocalDateTime;

public record PostResponse(
        Long postId,
        String status,
        LocalDateTime dateTime,
        String distributionPointId
){}
