package com.dali.security.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyRequest {
    private String email;
    private String code;
}
