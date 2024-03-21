package org.example.GlobalEcxeHandler;

import org.example.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//异常处理器
@RestControllerAdvice
public class GlobalEcxeHandler {
    @ExceptionHandler
    public Result Exception(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }
}
