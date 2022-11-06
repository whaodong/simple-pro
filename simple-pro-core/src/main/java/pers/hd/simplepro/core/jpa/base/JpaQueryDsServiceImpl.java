package pers.hd.simplepro.core.jpa.base;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * JPA定义实现类
 *
 *@author WangHaoDong
 */
public abstract class JpaQueryDsServiceImpl<T, ID extends Serializable, R extends JpaQueryDsDao<T, ID>>
        implements JpaQueryDsService<T, ID, R> {

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected R baseRepository;

    @Override
    public R getRepository() {
        return baseRepository;
    }

    @Override
    public boolean exists(ID id) {
        boolean result = baseRepository.existsById(id);
        JdbcClose();
        return result;
    }

    @Override
    public T find(ID id) {
        T result = baseRepository.findById(id).orElse(null);
        JdbcClose();
        return result;
    }

    @Override
    public Optional<T> get(ID id) {
        T result = baseRepository.findById(id).orElse(null);
        JdbcClose();
        return Optional.ofNullable(result);
    }

    @Override
    public List<T> findAll(Example<T> example) {
        List<T> result = baseRepository.findAll(example);
        JdbcClose();
        return result;
    }

    @Override
    public List<T> findAll() {
        List<T> result = baseRepository.findAll();
        JdbcClose();
        return result;
    }

    @Override
    public List<T> findAll(Predicate predicate, Sort sort) {
        Iterable<T> iterable = baseRepository.findAll(predicate, sort);
        if (iterable == null) {
            throw new NullPointerException();
        } else {

        }
        List<T> result = Lists.newArrayList(baseRepository.findAll(predicate, sort));
        JdbcClose();
        return result;
    }

    @Override
    public List<T> findAll(Predicate predicate) {
        List<T> result = Lists.newArrayList(baseRepository.findAll(predicate));
        JdbcClose();
        return result;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        Page<T> result = baseRepository.findAll(pageable);
        JdbcClose();
        return result;
    }

    @Override
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        Page<T> result = baseRepository.findAll(predicate, pageable);
        JdbcClose();
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T update(ID id, T entity) {
        T result = update(baseRepository, id, entity);
        JdbcClose();
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T update(T entity, T tdb) {
        BeanUtils.copyProperties(entity, tdb);
        return tdb;
    }

    @Override
    public T save(T entity) {
        T result = save(baseRepository, entity);
        JdbcClose();
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public T saveOrUpdate(ID id, T t) {
        if (id != null) {
            T db = find(id);
            if (db != null) {
                return update(id, t);
            }
        }
        return save(t);
    }

    @SuppressWarnings("all")
    @Override
    public void delete(ID id) {
        baseRepository.deleteById(id);
        JdbcClose();
    }

    private <TT, TID extends Serializable> TT update(JpaQueryDsDao<TT, TID> baseRepository, TID id, TT entity) {
        TT tdb = baseRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(entity, tdb);
        return tdb;
    }

    private <TT, TID extends Serializable> TT save(JpaQueryDsDao<TT, TID> baseRepository, TT entity) {
        TT result = baseRepository.save(entity);
        JdbcClose();
        return result;
    }

    private void JdbcClose(){
        em.close();
    }
}
