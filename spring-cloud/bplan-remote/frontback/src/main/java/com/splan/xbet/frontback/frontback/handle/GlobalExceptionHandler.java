package com.splan.xbet.frontback.frontback.handle;

import com.splan.base.enums.ResultStatus;
import com.splan.base.http.CommonResult;
import com.splan.base.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult<String> httpRequestMethodHandler() {
        return ResultUtil.returnError(ResultStatus.UNKNOWN_FUNCTION);
    }

    /**
     * 权限不足报错拦截
     */
    @ExceptionHandler(UnauthorizedException.class)
    public CommonResult<String> unauthorizedExceptionHandler() {
        return ResultUtil.returnError(ResultStatus.NOTPERMISSION);
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public CommonResult<String> unauthenticatedException() {
        return ResultUtil.returnError(ResultStatus.USER_NOT_LOGIN);
    }

    /**
     * 参数验证
     * @return
     */
    @ExceptionHandler(BindException.class)
    public CommonResult<String> handlerBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        return ResultUtil.returnError(ResultStatus.PARAM_EMPTY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        return ResultUtil.returnError(ResultStatus.PARAM_EMPTY);
    }

    @ExceptionHandler(Exception.class)
    public CommonResult<String> exceptionHandle(Exception e){
        log.error(e.getClass()+"=="+e.getMessage());
        return ResultUtil.returnError(ResultStatus.PARAM_ERROR);
    }

}
