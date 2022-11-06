package pers.hd.simplepro.core.jpa.base;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * JPA接口定义
 *
 *@author WangHaoDong
 */
public interface JpaQueryDsService<T, ID extends Serializable, R extends JpaQueryDsDao<T, ID>> {

    R getRepository();

    T find(ID id);

    Optional<T> get(ID id);

    boolean exists(ID id);

    List<T> findAll();

    List<T> findAll(Predicate predicate, Sort sort);

    List<T> findAll(Predicate predicate);

    List<T> findAll(Example<T> example);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Predicate predicate, Pageable pageable);

    T update(ID id, T entity);

    T update(T entity, T db);

    T save(T entity);

    T saveOrUpdate(ID id, T t);

    void delete(ID id);
}
