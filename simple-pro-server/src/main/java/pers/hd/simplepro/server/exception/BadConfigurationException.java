package pers.hd.simplepro.server.exception;

import pers.hd.simplepro.server.util.HttpStatus;

/**
 * @author WangHaoDong
 */
public class BadConfigurationException extends AbstractSimpleProException{
    public BadConfigurationException(String message) {
        super(message);
    }

    public BadConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
