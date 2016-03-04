package com.mycms.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author xias1
 * @param <T>
 */
public class ResponseData<T> {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("errorCode")
    private int errorCode;
    @JsonProperty("data")
    private T data;
    @JsonProperty("message")
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public ResponseData() {
        // emtpy
    }
    
    public ResponseData(boolean success) {
        this.success = success;
    }
    
    public ResponseData(boolean success, int errorCode, T data, String message) {
        this.success = success;
        this.errorCode = errorCode;
        this.data = data;
        this.message = message;
    }
    
    public ResponseData(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
    
    public static ResponseData successData() {
        return new ResponseData(true, 0, null, null);
    }
    
    public static ResponseData successData(Object data) {
        return new ResponseData(true, 0, data, null);
    }
    
    public static ResponseData successData(Object data, String message) {
        return new ResponseData(true, 0, data, message);
    }
    
    public static ResponseData errorData(String message) {
        return new ResponseData(false, null, message);
    }
    
    public static ResponseData errorData(int errorCode, String message) {
        return new ResponseData(false, errorCode, null, message);
    }
    
    public static ResponseData errorData(int errorCode) {
        return new ResponseData(false, errorCode, null, null);
    }
}
