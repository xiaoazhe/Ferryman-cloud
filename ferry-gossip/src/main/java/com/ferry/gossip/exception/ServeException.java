package com.ferry.gossip.exception;



/**
 * 自定义异常
 * @Author: 摆渡人
 * @Date: 2021/6/11
 */
public class ServeException extends RuntimeException {
    public ServeException(String message) {
        super(message);
    }

}
