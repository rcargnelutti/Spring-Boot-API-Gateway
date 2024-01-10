package com.example.springboot.gateway;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServiceRecordDto(@NotBlank String name, Boolean enabled, String port, String host, String path, String protocol) {
}
