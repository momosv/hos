package cn.momosv.hos.exception;

import cn.momosv.hos.bean.Msg;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.LoginException;

@ResponseBody
@SuppressWarnings("all")
@ControllerAdvice
public class GlobalExceptionHandler{

//    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandle.class);

    @ExceptionHandler
    private  Msg  exceptionHandle(Exception e) {
    	if (e instanceof LoginException) {
            return Msg.fail().add("msg", e.getMessage());
        }
        if (e instanceof NullPointerException) {
            return Msg.fail().add("msg", "参数空异常");
        }
        if (e instanceof MissingServletRequestParameterException) {
            return Msg.fail().add("msg", "MissingServletRequestParameterException");
        }
        return  Msg.fail().add("msg", "系统异常");
    }
}