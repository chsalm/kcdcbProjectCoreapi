package com.kcdcb.coreapi.config.component;

import com.kcdcb.coreapi.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class KcdCbHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        BaseResponse baseResponse = new BaseResponse(KcdCbSuccess.GET_SUCCESS);
        if(object instanceof Object[]) {
            Object[] objects = (Object[]) object;
            for(Object obj : objects) {
                baseResponse.getResultList().add(obj);
            }
        } else if(object instanceof List) {
            List objectList = (List) object;
            baseResponse.getResultList().addAll(objectList);
        } else if(object instanceof Map && ((Map)object).containsKey("error") && ((Map)object).containsKey("status")) {
            Map objectMap = (Map) object;
            String error = (String) objectMap.get("error");
            String status = String.valueOf((Integer)objectMap.get("status"));
            baseResponse = new BaseResponse(error, status);
        } else if(object instanceof BaseResponse) {
            baseResponse = (BaseResponse)object;
        } else {
            baseResponse.getResultList().add(object);
        }
        baseResponse.setCount(baseResponse.getResultList().size());
        if(!(object instanceof ErrorResponse)) {
            super.writeInternal(baseResponse, type, outputMessage);
        } else {
            super.writeInternal(object, type, outputMessage);
        }
    }
}
