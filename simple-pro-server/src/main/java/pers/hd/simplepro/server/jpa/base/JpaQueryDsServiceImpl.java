package pers.hd.simplepro.server.jpa.base;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
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
public abstract class JpaQueryDsServiceImpl<T, ID extends Serializable, R extends JpaQueryDsDao<T, ID>> implements JpaQueryDsService<T, ID, R> {

    @PersistenceContext
    protected EntityManager em;

    private final String domainName;

    @Autowired
    protected R baseRepository;

    protected JpaQueryDsServiceImpl() {
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
    public boolean exists(ID id) {
        log.info("进入 exists 判断");
        Assert.notNull(id, domainName + " Id 信息不能为空");
        boolean result = baseRepository.existsById(id);

        log.info(String.format("退出 exists 判断 %s id:%s 返回:%s", domainName, id, result));
        return result;
    }

    @Override
    public T update(T entity) {
        return baseRepository.saveAndFlush(entity);
    }

    @Override
    public T find(ID id) {
        log.info("进入 find 查询");
        Assert.notNull(id, domainName + " Id信息不能为空");
        T result = baseRepository.findById(id).orElse(null);
        log.info(String.format("退出 find 查询 %s id:%s 返回:%s", domainName, id, result));
        return result;
    }

    @Override
    public List<T> findAll() {
        log.info("进入 findAll 查询");
        List<T> result = baseRepository.findAll();
        log.info(String.format("退出 findAll 查询  返回:%s条数据", result.size()));
        return result;
    }

    @Override
    public List<T> findAll(Sort sort) {
        log.info("进入 findAll 查询");
        List<T> result = Lists.newArrayList(baseRepository.findAll(sort));
        log.info(String.format("退出 findAll 查询  返回:%s条数据", result.size()));
        return result;
    }

    @Override
    public List<T> findAll(Predicate predicate, Sort sort) {
        return Lists.newArrayList(baseRepository.findAll(predicate, sort));
    }

    @Override
    public List<T> findAll(Specification<T> predicate) {
        return Lists.newArrayList(baseRepository.findAll(predicate));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable) {
        return baseRepository.findAll(spec, pageable);
    }

    @Override
    public <Q> Page<T> f(Q criteria, Pageable pageable) {
        return baseRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root,query, cb,criteria), pageable);
    }


    @Override
    public T update(ID id, T entity) {
        T result = update(baseRepository, id, entity);
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
    }

    private <TT, TID extends Serializable> TT update(JpaQueryDsDao<TT, TID> baseRepository, TID id, TT entity) {
        TT tdb = baseRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(entity, tdb);
        return tdb;
    }

    private <TT, TID extends Serializable> TT save(JpaQueryDsDao<TT, TID> baseRepository, TT entity) {
        TT result = baseRepository.save(entity);
        return result;
    }

    @Override
    public List<T> createInBatch(Collection<T> domains) {
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
}
