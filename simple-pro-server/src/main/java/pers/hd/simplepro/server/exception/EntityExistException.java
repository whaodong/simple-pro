package pers.hd.simplepro.server.exception;

import org.springframework.util.StringUtils;
import pers.hd.simplepro.server.util.HttpStatus;

/**
 * @author WangHaoDong
 */
public class EntityExistException extends AbstractSimpleProException{
    public EntityExistException(Class clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
