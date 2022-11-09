package pers.hd.simplepro.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *   用于判断是否过滤数据权限
 *   1、如果没有用到 @OneToOne 这种关联关系，只需要填写 fieldName [参考：DeptQueryCriteria.class]
 *   2、如果用到了 @OneToOne ，fieldName 和 joinName 都需要填写，拿UserQueryCriteria.class举例:
 *   应该是 @DataPermission(joinName = "dept", fieldName = "id")
 * </p>
 *
 * @author Administrator*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {

    String fieldName() default "";

    String joinName() default "";
}