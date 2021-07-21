package com.lxh.bean;

import java.io.Serializable;

/**
 * 响应类
 * @author 八块肚腩
 */
public class ResultInfo<T> implements Serializable {
    private boolean flag;
    private String message;
    private T data;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public ResultInfo(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public ResultInfo(boolean flag, String message, T data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{" +
                "\'flag\'=" + flag +
                ", \'message\'='" + message + '\'' +
                ", \'data\'=" + data +
                '}';
    }
}
