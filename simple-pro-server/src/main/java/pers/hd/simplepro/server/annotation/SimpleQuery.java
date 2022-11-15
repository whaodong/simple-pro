package pers.hd.simplepro.server.annotation;

import pers.hd.simplepro.server.domain.model.query.UserQueryCriteria;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询工具类 通过在查询实体中增加{@code SimpleQuery}注解 实现单表查询
 * 例如: {@link UserQueryCriteria}
 *
 * @author wanghaodong
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleQuery {

    // 基本对象的属性名

    /**
     * 基本对象的属性名 如果查询属性与数据库实体查询属性不一致 那么需要指明数据库实体属性
     */
    String propName() default "";

    /**
     * 查询的方式 {@link Type}
     */
    Type type() default Type.EQUAL;

    /**
     * 多字段模糊搜索，仅支持String类型字段，多个用逗号隔开, 如@Query(blurry = "email,username")
     */
    String blurry() default "";

    /**
     * 指明查询的类型
     * <li>EQUAL(相等)</li>
     * <li>GREATER_THAN(大于等于)</li>
     * <li>LESS_THAN(小于等于)</li>
     * <li>INNER_LIKE(中模糊查询)</li>
     * <li>LEFT_LIKE(左模糊查询)</li>
     * <li>RIGHT_LIKE(右模糊查询)</li>
     * <li>LESS_THAN_NQ(小于)</li>
     * <li>IN(包含)</li>
     * <li>NOT_EQUAL(不等于)</li>
     * <li>BETWEEN(间隔查询)</li>
     * <li>NOT_NULL(不为空)</li>
     * <li>IS_NULL(为空)</li>
     *
     * @author wanghaodong
     */
    enum Type {
        EQUAL, GREATER_THAN, LESS_THAN, INNER_LIKE, LEFT_LIKE, RIGHT_LIKE, LESS_THAN_NQ, IN, NOT_EQUAL, BETWEEN, NOT_NULL, IS_NULL
    }

}
