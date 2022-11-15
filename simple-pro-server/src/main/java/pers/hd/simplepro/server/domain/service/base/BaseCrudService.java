package pers.hd.simplepro.server.domain.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import pers.hd.simplepro.server.domain.repository.base.BaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * JPA接口定义
 *
 *@author WangHaoDong
 */
public interface BaseCrudService<T, ID extends Serializable, R extends BaseRepository<T, ID>> {

    R getRepository();

    T find(ID id);

    boolean existsById(ID id);

    List<T> findAll();

    Page<T> findAllByPage(Pageable pageable);

    List<T> findAllBySort(Sort sort);

    <Q> List<T> findAllByQueryAndSort(Q criteria,Sort sort);

    <Q> Page<T> findAllByQueryAndPage(Q criteria, Pageable pageable);

    List<T> findAllByPredicate(Specification<T> predicate);

    Page<T> findAllByPredicateAndPage(@Nullable Specification<T> spec, Pageable pageable);

    T update(T entity);

    T save(T entity);

    void delete(ID id);

    @Transactional
    List<T> createInBatch(@NonNull Collection<T> domains);

    @NonNull
    @Transactional
    List<T> updateInBatch(@NonNull Collection<T> domains);

    @Transactional
    void deleteByIdInBatch(@NonNull Collection<ID> ids);
}
