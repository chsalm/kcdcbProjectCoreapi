package com.kcdcb.coreapi.exception;

import com.kcdcb.coreapi.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
//@Controller
//@RequestMapping({"${server.error.path:${error.path:/error}}"})
//TODO ErrorController 처리 방식 변경 된걸로 추정. 추후 해결 필요.
public class KcdCbErrorController extends BasicErrorController {

    public KcdCbErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

    public KcdCbErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity(status);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.ALL)));
            return new ResponseEntity(errorResponse, status);
        }
    }
}
