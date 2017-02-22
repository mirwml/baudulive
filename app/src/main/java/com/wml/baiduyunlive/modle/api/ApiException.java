package com.wml.baiduyunlive.modle.api;

/**
 * Created by 王苗亮 on 2017/2/20.
 * 自定义异常类
 */

public class ApiException extends RuntimeException{

    private static String message;

    /**
     * 根据返回码来确定是什么异常
     * @param resultCode
     */
    public ApiException(int resultCode)
    {
        this(getApiExceptionMessage(resultCode));
    }

    /**
     * 根据返回的字符串结果来判断是哪一种异常
     * @param detailMessage
     */
    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }

    private static String getApiExceptionMessage(int code){
        switch (code) {
             case ApiConfig.ERROE_CODE_1:
                 message = ApiConfig.ERROE_MESSAGE_1;
                 break;
            case ApiConfig.ERROE_CODE_2:
                message = ApiConfig.ERROE_MESSAGE_2;
                break;
            case ApiConfig.ERROE_CODE_3:
                message = ApiConfig.ERROE_MESSAGE_3;
                break;
            case ApiConfig.ERROE_CODE_4:
                message = ApiConfig.ERROE_MESSAGE_4;
                break;
            case ApiConfig.ERROE_CODE_5:
                message = ApiConfig.ERROE_MESSAGE_5;
                break;
        }
        return message;
    }
}
