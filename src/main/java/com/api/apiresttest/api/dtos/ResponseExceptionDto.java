package com.api.apiresttest.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseExceptionDto {
    private String code; 
    private String errorMessage;
}
