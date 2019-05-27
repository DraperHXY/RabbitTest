package com.draper.common.util;

/**
 * @author draper_hxy
 */
public class ResultUtil {

    public final static Integer SUCCESS_CODE = 0;//成功编码
    public final static String SUCCESS_MSG = "成功";//成功
    public final static Integer ERROR_CODE = 1;//异常编码
    public final static String ERROR_MSG = "失败";//失败
    public final static Integer OTHER_CODE = 2;//其它

    /**
     * 成功
     * @param msg
     * @param data
     * @return
     */
    public static <T> StatusResult<T> success(String msg, T data){
        return new StatusResult<>(SUCCESS_CODE, msg, data);
    }

    public static <T> StatusResult<T> success(String msg){
        return success(msg, null);
    }

    public static <T> StatusResult<T> success(){
        return success(SUCCESS_MSG);
    }

    public static <T> StatusResult<T> success(T data){
        return success(SUCCESS_MSG, data);
    }

    /**
     * 失败
     * @param msg
     * @param data
     * @return
     */
    public static <T> StatusResult<T> error(String msg, T data){
        return new StatusResult<>(ERROR_CODE, msg, data);
    }

    public static <T> StatusResult<T> error(String msg){
        return error(msg, null);
    }

    public static <T> StatusResult<T> error(){
        return error(ERROR_MSG);
    }

    /**
     * 其它错误
     * @param msg
     * @return
     */
    public static <T> StatusResult<T> other(String msg){
        return new StatusResult<>(OTHER_CODE, msg, null);
    }


}

