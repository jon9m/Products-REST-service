package com.mmk.rest.restapi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDetails {
    private String code;
    private String description;
}
