//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.syswin.systoon.framework.bean;

public class ResponseResult<T> extends BaseBean {
    private String code;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(T data) {
        this("0", "成功", data);
    }

    public ResponseResult(String code, String message) {
        this(code, message, null);
    }

    public ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult(data);
    }

    public static <T> ResponseResult<T> fail(String code, String message, T data) {
        return new ResponseResult(code, message, data);
    }

    public static <T> ResponseResult<T> fail(String code, String message) {
        return new ResponseResult(code, message);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
