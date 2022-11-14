package pers.hd.simplepro.server.exception;

import pers.hd.simplepro.server.util.HttpStatus;

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
