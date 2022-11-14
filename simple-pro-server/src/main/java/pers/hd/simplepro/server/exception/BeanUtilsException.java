package pers.hd.simplepro.server.exception;


import pers.hd.simplepro.server.util.HttpStatus;

/**
 * BeanUtils exception.
 *
 * @author WangHaoDong
 */
public class BeanUtilsException extends AbstractSimpleProException {

    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
