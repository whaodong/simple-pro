package pers.hd.simplepro.server.domain.service.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import pers.hd.simplepro.server.domain.repository.base.BaseRepository;
import pers.hd.simplepro.server.util.QueryHelp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * JPA定义实现类
 *
 * @author WangHaoDong
 */
@Slf4j
public abstract class AbstractCrudService<T, ID extends Serializable, R extends BaseRepository<T, ID>>
        implements BaseCrudService<T, ID, R> {

    @PersistenceContext
    protected EntityManager em;

    private final String domainName;

    @Autowired
    protected R baseRepository;

    protected AbstractCrudService() {
        @SuppressWarnings("unchecked")
        Class<T> domainClass = (Class<T>) fetchType();
        domainName = domainClass.getSimpleName();
    }

    /**
     * 获取实际的泛型类型。
     *
     * @return 将返回真正的泛型类型
     */
    private Type fetchType() {
        return ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public R getRepository() {
        return baseRepository;
    }

    @Override
    public boolean existsById(ID id) {
        Assert.notNull(id, domainName + " Id 信息不能为空");
        return baseRepository.existsById(id);
    }

    @Override
    public T update(T entity) {
        Assert.notNull(entity, domainName + " data must not be null");
        return baseRepository.saveAndFlush(entity);
    }

    @Override
    public T find(ID id) {
        Assert.notNull(id, domainName + " Id信息不能为空");
        return baseRepository.findById(id).orElse(null);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> findAllBySort(Sort sort) {
        return baseRepository.findAll(sort);
    }

    @Override
    public List<T> findAllByIds(Collection<ID> ids) {
        return CollectionUtils.isEmpty(ids) ? Collections.emptyList() : baseRepository.findAllById(ids);
    }

    @Override
    public <Q> List<T> findAllByQueryAndSort(Q criteria, Sort sort) {
        return baseRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, query, cb, criteria), sort);
    }

    @Override
    public Page<T> findAllByPage(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Override
    public List<T> findAllByPredicate(Specification<T> predicate) {
        return baseRepository.findAll(predicate);
    }

    @Override
    public Page<T> findAllByPredicateAndPage(@Nullable Specification<T> spec, Pageable pageable) {
        return baseRepository.findAll(spec, pageable);
    }

    @Override
    public <Q> Page<T> findAllByQueryAndPage(Q criteria, Pageable pageable) {
        return baseRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, query, cb, criteria), pageable);
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @SuppressWarnings("all")
    @Override
    public void delete(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    @Override
    public void deleteAll(Collection<T> domains) {
        if (CollectionUtils.isEmpty(domains)) {
            log.debug(domainName + " collection is empty");
            return;
        }
        baseRepository.deleteInBatch(domains);
    }

    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }

    @Override
    public List<T> saveInBatch(Collection<T> domains) {
        return CollectionUtils.isEmpty(domains) ? Collections.emptyList() :
                baseRepository.saveAll(domains);
    }

    @Override
    public List<T> updateInBatch(Collection<T> domains) {
        return CollectionUtils.isEmpty(domains) ? Collections.emptyList() :
                baseRepository.saveAll(domains);
    }

    @Override
    public void deleteByIdInBatch(Collection<ID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            log.debug(domainName + " id collection is empty");
            return;
        }
        baseRepository.deleteAllById(ids);
    }

    @Override
    public long count() {
        return baseRepository.count();
    }
}
