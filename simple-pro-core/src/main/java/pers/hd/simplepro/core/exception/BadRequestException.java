package pers.hd.simplepro.core.exception;

import pers.hd.simplepro.core.util.HttpStatus;

/**
 * @author WangHaoDong
 */
public class BadRequestException extends AbstractSimpleProException{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
