package pers.hd.simplepro.server.annotation;

import java.lang.annotation.*;

/**
 * 匿名访问基础类
 *
 * @author wanghaodong
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
