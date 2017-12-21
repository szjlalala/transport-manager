package com.tms.exception.handler;

import com.tms.common.BizException;
import com.tms.common.Results;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.AccessDeniedException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by szj on 2017/11/10.
 */
@ControllerAdvice(annotations = Controller.class)
@Order(2)
public class GlobalExceptionHandler {
    private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * 对于所有参数进行转换
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

    /**
     * 处理所有不可知的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    ModelAndView handleException(Exception e) {
        logger.error("Exception stack :", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("myException", Results.ErrorCode.SERVER_ERROR);
        mav.setViewName("error");
        return mav;
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    ModelAndView handleBizException(BizException e) {
        logger.error("Biz error stack :", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("myException", e.getErrorCode());
        mav.setViewName("error");
        return mav;
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     * @Valid or @Validated 会抛出MethodArgumentNotValidException类型的Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("Invalid parameter stack :", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("myException", Results.ErrorCode.INVALID_PARAMETER);
        mav.setViewName("error");
        return mav;
    }

    /**
     * 处理权限异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    ModelAndView SecurityException(AccessDeniedException e) {
        logger.error("Biz security stack :", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("myException", Results.ErrorCode.INVALID_PERMISSIONS);
        mav.setViewName("error");
        return mav;
    }

}
