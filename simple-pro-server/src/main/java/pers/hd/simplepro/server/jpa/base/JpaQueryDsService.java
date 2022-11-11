package pers.hd.simplepro.server.jpa.base;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * JPA接口定义
 *
 *@author WangHaoDong
 */
public interface JpaQueryDsService<T, ID extends Serializable, R extends JpaQueryDsDao<T, ID>> {

    R getRepository();

    T find(ID id);

    boolean exists(ID id);

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAll(Predicate predicate, Sort sort);

    List<T> findAll(Specification<T> predicate);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

   <Q> Page<T> f(Q criteria, Pageable pageable);

    T update(ID id, T entity);

    T update(T entity);

    T update(T entity, T db);

    T save(T entity);

    T saveOrUpdate(ID id, T t);

    void delete(ID id);
}
