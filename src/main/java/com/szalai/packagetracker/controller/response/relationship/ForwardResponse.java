package com.szalai.packagetracker.controller.response.relationship;

import java.time.LocalDateTime;

public record ForwardResponse(
        Long forwardId,
        String status,
        LocalDateTime dateTime,
        String warehouseId
){}
