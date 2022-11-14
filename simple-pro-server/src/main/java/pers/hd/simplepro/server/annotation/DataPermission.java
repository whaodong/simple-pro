package pers.hd.simplepro.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 用于判断是否过滤数据权限
 * 填写 fieldName [参考：DeptQueryCriteria.class]
 * </p>
 *
 * @author Administrator
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {

    String fieldName() default "";

}