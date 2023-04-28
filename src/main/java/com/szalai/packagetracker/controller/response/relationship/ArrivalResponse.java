package com.szalai.packagetracker.controller.response.relationship;

import java.time.LocalDateTime;

public record ArrivalResponse(
        Long arrivalId,
        String status,
        LocalDateTime dateTime,
        String warehouseId
){}
