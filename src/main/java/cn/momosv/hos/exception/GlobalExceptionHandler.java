package cn.momosv.hos.exception;

import cn.momosv.hos.model.base.Msg;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.LoginException;

@ResponseBody
@SuppressWarnings("all")
@ControllerAdvice
public class GlobalExceptionHandler{

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    private Msg exceptionHandle(Exception e) {
        e.printStackTrace();
        if (e instanceof LoginException) {
            return Msg.fail().add("msg", e.getMessage());
        }
        if (e instanceof NullPointerException) {
            return Msg.fail().add("msg", e.getMessage());
        }
        if (e instanceof MissingServletRequestParameterException) {
            return Msg.fail().add("msg", e.getMessage());
        }
        return  Msg.fail().add("msg",e.getMessage());
    }
}