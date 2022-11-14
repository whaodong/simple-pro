package pers.hd.simplepro.server.exception;

import org.springframework.util.StringUtils;
import pers.hd.simplepro.server.util.HttpStatus;

/**
 * @author wanghaodong
 */
public class EntityNotFoundException extends AbstractSimpleProException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " does not exist";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
