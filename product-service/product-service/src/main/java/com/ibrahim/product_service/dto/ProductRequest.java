package com.ibrahim.product_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        String id,
        String name,
        String description,
        BigDecimal price
) {
}
