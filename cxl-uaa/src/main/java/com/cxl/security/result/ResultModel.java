package com.cxl.security.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author cxl
 * @description 响应结果
 * @date 2020/09/09 0009
 */
@Data
public class ResultModel<T> implements Serializable {


    private static final long serialVersionUID = -3672471466072263630L;

    private static final Integer CODE_OK = 200;

    private int code = CODE_OK;
    private String msg = "";
    private T result;




    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ResultModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
