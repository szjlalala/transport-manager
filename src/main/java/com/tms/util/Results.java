package com.tms.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Maps;

import java.io.Serializable;

/**
 * 
 * @Description 响应结果集类
 */
@JsonInclude(Include.ALWAYS)
public class Results implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 8282343803636762944L;

    /** 用户code */
    public static final int MODUEL_CODE_USER = 10;

    /** 场馆code */
    public static final int MODUEL_CODE_VENUE = 20;

    /** 票房规划code */
    public static final int MODUEL_CODE_DESIGN = 30;

    /** 票样code */
    public static final int MODUEL_CODE_TICKETMAP = 40;    

    /** API-code */
    public static final int MODUEL_CODE_API = 50;    

    /** 商品code */
    public static final int MODUEL_CODE_PRODUCT = 60;

    /** 响应成功code */
    public static final int SUCCESS_CODE = 0;

    /** 系统级错误code */
    public static final int PARAM_ERROR_CODE = -1;

    /** 系统级错误msg */
    public static final String PARAM_ERROR_MSG = "参数错误!";

    /** 成功提示 */
    public static final String SUCCESS_MSG = "操作成功！";

    /** 成功提示 */
    public static final String FIALURE_MSG = "操作失败！";

    /**
     * 响应结果信息
     */
    private Result result;

    /**
     * 响应数据信息
     */
    private Object data;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Results() {
        this.result = new Result(PARAM_ERROR_CODE, PARAM_ERROR_MSG);// 默认设置结果code和message
        this.data = Maps.newHashMap();
    }

    public Results(int code, String message, Object data) {
        this.result = new Result(code, message);
        this.data = data;
    }

    public Results(Result result, Object data) {
        this.result = result;
        this.data = data;
    }

    @JsonIgnore
    public void setCode(int code) {
        this.result.setCode(code);
    }

    @JsonIgnore
    public void setMessage(String message) {
        this.result.setMessage(message);
    }

    @JsonIgnore
    public void setSuccess() {
        setCode(SUCCESS_CODE);
        setMessage(SUCCESS_MSG);
    }

    @JsonIgnore
    public boolean isSuccess() {
        if (this.result.getCode() == SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }

    @JsonInclude(Include.ALWAYS)
    public class Result implements Serializable {

        /** serialVersionUID */
        private static final long serialVersionUID = -8292116512944157635L;

        /**
         * 成功为0，否则为5位数字(如10000 ) 前两位标识(10为用户模块，20为场馆，30票房规划，40票样)
         * 后面3位为流水号，格式为001，002 ...
         */
        private int code;

        /**
         * 成功和失败信息描述
         */
        private String message;

        public Result(int code) {
            super();
            this.code = code;
        }

        public Result(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "{\"code\":" + code + ", \"message\":\"" + message + "\"}";
        }

    }

}
