package com.kcdcb.coreapi.config.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KcdCbSuccess {
    GET_SUCCESS("S0001", "GET SUCCESS"),
    INSERT_SUCCESS("S0002", "INSERT SUCCESS"),
    UPDATE_SUCCESS("S0003", "UPDATE SUCCESS"),
    DELETE_SUCCESS("S0004", "DELETE SUCCESS"),
    PROCESS_SUCCESS("S0005", "PROCESS SUCCESS"),
    REQUEST_SUCCESS("S0006", "REQUEST SUCCESS");

    private String code;
    private String message;
}
