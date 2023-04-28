package com.szalai.packagetracker.controller.response.relationship;

import java.time.LocalDateTime;

public record DistributionResponse(
        Long distributionId,
        String status,
        LocalDateTime dateTime,
        String warehouseId
){}
