package pers.hd.simplepro.core.exception;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pers.hd.simplepro.core.util.HttpStatus;

/**
 * @author WangHaoDong
 */
public abstract class AbstractSimpleProException extends RuntimeException {

    /**
     * Error errorData.
     */
    private Object errorData;

    public AbstractSimpleProException(String message) {
        super(message);
    }

    public AbstractSimpleProException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public AbstractSimpleProException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
