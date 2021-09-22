package com.kcdcb.coreapi.exception;

import com.kcdcb.coreapi.exception.error.KcdCbError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KcdCbException extends Exception{
    private KcdCbError kcdCbError;
}
