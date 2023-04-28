package com.szalai.packagetracker.controller.response;

import java.time.LocalDateTime;

public record DistributionResponse(
        Long distributionId,
        String status,
        LocalDateTime dateTime,
        String warehouseId
){}
