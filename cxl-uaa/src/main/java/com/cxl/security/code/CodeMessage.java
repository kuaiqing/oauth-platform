package com.cxl.security.code;


/**
 *
 * @author cxl
 */
public enum CodeMessage {


    SUCCESS(200,"成功"),
    FAIL(1000,"失败");

    private int code;
    private String message;

    CodeMessage(int code,String message){
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
}
