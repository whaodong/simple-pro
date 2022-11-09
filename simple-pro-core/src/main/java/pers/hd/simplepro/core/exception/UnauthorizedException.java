package pers.hd.simplepro.core.exception;

import pers.hd.simplepro.core.util.HttpStatus;

/**
 * @author WangHaoDong
 */
public class UnauthorizedException extends AbstractSimpleProException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
