package com.tms.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by szj on 2017/11/10.
 */
@ApiModel
@JsonInclude(Include.ALWAYS)
public class Results<T> implements Serializable {

    @ApiModelProperty(value = "网关返回码", name = "网关返回码")
    private String code;

    @ApiModelProperty(value = "网关返回码描述", name = "网关返回码描述")
    private String msg;

    @ApiModelProperty(value = "业务返回码", name = "业务返回码")

    private String sub_code;

    @ApiModelProperty(value = "业务返回码描述", name = "业务返回码描述")
    private String sub_msg;

    @ApiModelProperty(value = "数据对象", name = "数据对象")
    private T result;

    public Results(ErrorCode errorCode) {
        this.code = CODE.INTERNAL_SERVER_ERROR.getCode();
        this.msg = CODE.INTERNAL_SERVER_ERROR.getMsg();
        this.sub_code = errorCode.getCode();
        this.sub_msg = errorCode.getMessage();
    }

    public Results(CODE code, ErrorCode errorCode) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.sub_code = errorCode.getCode();
        this.sub_msg = errorCode.getMessage();
    }

    public Results(CODE code, ErrorCode errorCode, T result) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.sub_code = errorCode.getCode();
        this.sub_msg = errorCode.getMessage();
        this.result = result;
    }

    public Results(String code, String msg, String sub_code, String sub_msg, T result) {
        this.code = code;
        this.msg = msg;
        this.sub_code = sub_code;
        this.sub_msg = sub_msg;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Results{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", sub_code='" + sub_code + '\'' +
                ", sub_msg='" + sub_msg + '\'' +
                ", result=" + result +
                '}';
    }

    public static <T> Results<T> setSuccessMessage(T result) {
        return new Results(
                CODE.SUCCESS.getCode(), CODE.SUCCESS.getMsg(),
                ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(),
                result);
    }

    /**
     * api返回码优先使用具体错误编码，如401，503等，不符合具体原因的错误才使用通用编码。
     * 该编码影响http response的http code
     */
    public enum CODE {

        SUCCESS(200, "接口调用成功"),

        /**
         * 参数错误，参数缺失时返回（通用请求错误处理，协议错误，参数错误，验证错误等）
         */
        BAD_REQUEST(400, "错误的请求"),

        /**
         * 登陆校验失败，或者api token校验失败时返回
         */
        UNAUTHORIZED(401, "授权验证失败"),

        /**
         * 权限不足，或访问未授权的资源时返回
         */
        FORBIDDEN(403, "拒绝访问"),

        /**
         * 资源未找到时返回
         */
        NOT_FOUND(404, "资源未找到"),

        /**
         * http 方法不支持时返回
         */
        METHOD_NOT_ALLOWED(405, "方法不支持"),

        /**
         * 参数编码格式不正确时返回，api接口不是json编码时返回
         */
        UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),

        /**
         * 内部服务错误（通用服务器错误）
         */
        INTERNAL_SERVER_ERROR(500, "服务错误"),

        /**
         * 接口可访问，但未实现不可用时返回
         */
        NOT_IMPLEMENTED(501, "服务未实现"),

        /**
         * 服务不可用，或者服务过载时返回
         */
        SERVICE_UNAVAILABLE(503, "服务不可用");

        /**
         * same as http status code
         */
        private int code;
        private String msg;

        CODE(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int code() {
            return code;
        }

        public String getCode() {
            return String.valueOf(code);
        }

        public String getMsg() {
            return msg;
        }

    }


    public enum ErrorCode {
        SUCCESS(CODE.SUCCESS.code + "00", "操作成功"),
        MISSING_PARAMETER(CODE.BAD_REQUEST.code + "01", "缺少必选参数"),
        INVALID_PARAMETER(CODE.BAD_REQUEST.code + "02", "非法的参数"),
        MISSING_HEADER(CODE.BAD_REQUEST.code + "03", "缺少必选参数(x-header)"),
        INVALID_TEMPLATE_VARIABlE_DEFIND(CODE.BAD_REQUEST.code + "04", "非法的模版参数变量定义"),
        INVALID_TEMPLATE_VARIABlE(CODE.BAD_REQUEST.code + "05", "非法的模版参数变量"),
        SIGNED_ERROR(CODE.UNAUTHORIZED.code + "01", "签名错误"),
        INVALID_PASSWORD(CODE.UNAUTHORIZED.code + "02", "原密码错误"),
        INVALID_PERMISSIONS(CODE.UNAUTHORIZED.code + "03", "操作权限不足"),
        INVALID_AUTH(CODE.UNAUTHORIZED.code + "04", "授权失败"),
        INVALID_APPINFO(CODE.UNAUTHORIZED.code + "05", "App不可用"),
        OVER_LIMIT_API(CODE.FORBIDDEN.code + "01", "超出调用限额"),
        OVER_LIMIT_SMS_PHONE(CODE.FORBIDDEN.code + "02", "超出短信发送限额"),
        ORDER_NOT_EXIST(CODE.NOT_FOUND.code + "01", "订单不存在"),
        DRIVER_NOT_EXIST(CODE.NOT_FOUND.code + "02", "模版分类不存在"),
        VEHICLE_NOT_EXIST(CODE.NOT_FOUND.code + "03", "模版分类不存在"),
        SERVER_ERROR(CODE.INTERNAL_SERVER_ERROR.code + "00", "服务错误"),
        BIZ_FAIL(CODE.INTERNAL_SERVER_ERROR.code + "01", "业务处理失败");


        private final String code;
        private final String message;

        ErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    private static ImmutableMap<Integer, CODE> codeMap;

    static {
        ImmutableMap.Builder<Integer, CODE> builder = ImmutableMap.builder();
        codeMap = builder
                .put(CODE.SUCCESS.code(), CODE.SUCCESS)
                .put(CODE.BAD_REQUEST.code(), CODE.BAD_REQUEST)
                .put(CODE.UNAUTHORIZED.code(), CODE.UNAUTHORIZED)
                .put(CODE.FORBIDDEN.code(), CODE.FORBIDDEN)
                .put(CODE.NOT_FOUND.code(), CODE.NOT_FOUND)
                .put(CODE.METHOD_NOT_ALLOWED.code(), CODE.METHOD_NOT_ALLOWED)
                .put(CODE.UNSUPPORTED_MEDIA_TYPE.code(), CODE.UNSUPPORTED_MEDIA_TYPE)
                .put(CODE.INTERNAL_SERVER_ERROR.code(), CODE.INTERNAL_SERVER_ERROR)
                .put(CODE.NOT_IMPLEMENTED.code(), CODE.NOT_IMPLEMENTED)
                .put(CODE.SERVICE_UNAVAILABLE.code(), CODE.SERVICE_UNAVAILABLE)
                .build();
    }

    public static CODE ofCode(int sc) {
        return codeMap.getOrDefault(sc, null);
    }
}
