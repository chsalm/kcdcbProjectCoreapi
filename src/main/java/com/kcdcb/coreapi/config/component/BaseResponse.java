package com.kcdcb.coreapi.config.component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BaseResponse {
    private List resultList = new ArrayList<>();
    private int count;
    private final String message;
    private final String code;

    public BaseResponse(KcdCbSuccess kcdCbSuccess) {
        this.code = kcdCbSuccess.getCode();
        this.message = kcdCbSuccess.getMessage();
    }

}
