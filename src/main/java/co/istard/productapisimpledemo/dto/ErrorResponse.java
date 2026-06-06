package co.istard.productapisimpledemo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse<T> (
        LocalDateTime timestamp,
        String message,
        T errors,
        Integer status
){ }
