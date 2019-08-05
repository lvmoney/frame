package com.lvmoney.oauth2.center.server.exception;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {
    /**
     *
     */
    private static final long serialVersionUID = 4158670139382486655L;

    public VerificationCodeException(String msg) {
        super(msg);
    }

    public VerificationCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
