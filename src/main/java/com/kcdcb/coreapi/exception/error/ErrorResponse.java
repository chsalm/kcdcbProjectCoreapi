package com.kcdcb.coreapi.exception.error;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;

import java.util.Map;

@Getter
@Slf4j
public class ErrorResponse {
    public ErrorResponse(KcdCbError kcdCbError) {
        this.errorCode = kcdCbError.getErrorCode();
        this.errorMessage = kcdCbError.getErrorMessage();
    }

    public ErrorResponse(BindException e) {
        this.errorCode = "BindError";
        this.errorMessage = e.getFieldError().getField() + " " + e.getFieldError().getDefaultMessage();
    }

    private String errorCode;
    private String errorMessage;

    public ErrorResponse(Map<String, Object> errorAttributes) {
        this.errorCode = (String)errorAttributes.get("status");
        this.errorMessage = (String)errorAttributes.get("error");
    }
}
