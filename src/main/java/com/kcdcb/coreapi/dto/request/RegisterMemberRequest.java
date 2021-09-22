package com.kcdcb.coreapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterMemberRequest {
    @NotBlank
    private String name;
}
