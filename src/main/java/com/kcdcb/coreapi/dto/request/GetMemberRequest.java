package com.kcdcb.coreapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetMemberRequest {
    @NotNull
    private Long id;
}
