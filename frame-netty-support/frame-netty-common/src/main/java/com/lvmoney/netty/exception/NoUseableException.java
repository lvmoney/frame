package com.lvmoney.netty.exception;

/**
 * 没有可用的通道异常
 * <p>
 *
 * @author lvmoney at
 * @date 2018/11/2-16:00
 */
public class NoUseableException extends RuntimeException {
    private static final long serialVersionUID = 7762465537123947683L;

    public NoUseableException() {
        super();
    }

    public NoUseableException(String message) {
        super(message);
    }
}
