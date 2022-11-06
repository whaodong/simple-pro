package pers.hd.simplepro.core.exception;


import pers.hd.simplepro.core.util.HttpStatus;

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
