package com.tms.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.common.BizException;
import com.tms.common.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szj on 2017/11/10.
 */
@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalRestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

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
    Results handleException(HttpServletResponse response, Exception e) {

        logger.error("Exception stack :", e);

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        return new Results(Results.ErrorCode.SERVER_ERROR);
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Results handleBizException(HttpServletResponse response, BizException e) {

        logger.error("Biz error stack :", e);
        String errCode = e.getErrorCode().getCode();

        int sc;
        try {
            sc = Integer.valueOf(errCode.substring(0, 3));
        } catch (NumberFormatException ne) {
            logger.warn("get http status code from ErrorCode occur error!");
            sc = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }

        response.setStatus(sc);

        return new Results(Results.ofCode(sc), e.getErrorCode());
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     * @Valid or @Validated 会抛出MethodArgumentNotValidException类型的Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Results handleMethodArgumentNotValidException(HttpServletResponse response,
                                                  MethodArgumentNotValidException e) {

        logger.debug("Invalid parameter stack :", e);

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return new Results(Results.CODE.BAD_REQUEST, Results.ErrorCode.INVALID_PARAMETER);
    }

    /**
     * 不支持的媒体编码类型
     *
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    Results handleHttpMediaTypeNotAcceptableException(HttpServletResponse response,
                                                      HttpMediaTypeNotAcceptableException e) {
        logger.debug("media type not acceptable stack :", e);

        response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);

        return new Results(Results.CODE.UNSUPPORTED_MEDIA_TYPE, Results.ErrorCode.INVALID_PARAMETER);
    }

    /**
     * bean validate 错误处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    Results handleBeanValidationException(HttpServletResponse response,
                                          ConstraintViolationException e) {
        logger.debug("Bean invalid stack: ", e);

        Map<String, String> errInfo = new HashMap<>();

        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
            errInfo.put(
                    constraintViolation.getPropertyPath().toString(),
                    constraintViolation.getMessage());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.info("参数检验错误: {}", mapper.writeValueAsString(errInfo));
        } catch (JsonProcessingException e1) {
            logger.warn("JsonProcessingException: {}", e1.getMessage());
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return new Results(Results.CODE.BAD_REQUEST, Results.ErrorCode.INVALID_PARAMETER,
                null);
    }
}
