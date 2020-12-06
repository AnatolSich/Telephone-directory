package com.springAdvanced.Telephonedirectory.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneNumberDto {
    private String number;
    private Long userId;
    private Long companyId;
}
