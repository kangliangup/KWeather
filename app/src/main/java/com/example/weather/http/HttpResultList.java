package com.example.weather.http;

/**
 * Date：2018/2/7
 * Author：小康de生活
 * Signature:好好学习，天天向上
 * Description:
 */

public class HttpResultList<T>  {
    public static final String OK="0";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private T list;
    private String status;
    private String msg;



    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
