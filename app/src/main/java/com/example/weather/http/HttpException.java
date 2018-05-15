package com.example.weather.http;

/**
 * Date：2018/2/9
 * Author：小康de生活
 * Signature:好好学习，天天向上
 * Description:
 */

public class HttpException extends RuntimeException {
    public String code;
    public String message;

    public HttpException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
