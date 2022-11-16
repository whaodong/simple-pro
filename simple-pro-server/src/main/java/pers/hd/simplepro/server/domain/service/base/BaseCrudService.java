package pers.hd.simplepro.server.domain.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import pers.hd.simplepro.server.domain.repository.base.BaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * JPA接口定义 CrudService接口包含一些常用方法。
 *
 * @author WangHaoDong
 */
public interface BaseCrudService<T, ID extends Serializable, R extends BaseRepository<T, ID>> {

    /**
     * 获得访问底层数据模型
     *
     * @return R 返回访问底层数据模型
     */
    R getRepository();

    /**
     * 根据id查询数据
     *
     * @param id 主键id
     * @return T 返回实体对象
     * @author WangHaodong
     */
    T find(ID id);

    /**
     * 判段id是否存在
     *
     * @param id 主键id
     * @return boolean 存在返回 true 反之 false
     * @author WangHaodong
     */
    boolean existsById(ID id);

    /**
     * 查询所有数据
     *
     * @return java.util.List<T> 数据集合
     * @author wanghaodong
     */
    List<T> findAll();

    /**
     * 分页查询所有数据
     *
     * @param pageable 分页实体
     * @return org.springframework.data.domain.Page<T> 分页数据对象
     * @author wanghaodong
     */
    Page<T> findAllByPage(Pageable pageable);

    /**
     * 查询所有数据并排序
     *
     * @param sort 排序对象
     * @return org.springframework.data.domain.Page<T> 分页数据对象
     * @author wanghaodong
     */
    List<T> findAllBySort(Sort sort);

    /**
     * 根据id集合查询数据
     *
     * @param ids 主键id集合
     * @return org.springframework.data.domain.Page<T> 分页数据对象
     * @author wanghaodong
     */
    List<T> findAllByIds(Collection<ID> ids);

    /**
     * 条件查询并排序
     *
     * @param criteria 查询条件
     * @param sort     排序对象
     * @return java.util.List<T> 数据集合
     * @author wanghaodong
     */
    <Q> List<T> findAllByQueryAndSort(Q criteria, Sort sort);

    /**
     * 条件查询分页
     *
     * @param criteria 查询条件实体
     * @param pageable 分页对象
     * @return java.util.List<T> 分页数据集合
     * @author wanghaodong
     */
    <Q> Page<T> findAllByQueryAndPage(Q criteria, Pageable pageable);

    /**
     * 复杂查询
     *
     * @param predicate 复杂查询表达式
     * @return java.util.List<T>
     * @author wanghaodong
     */
    List<T> findAllByPredicate(Specification<T> predicate);

    /**
     * 复杂查询 分页
     *
     * @param spec     复杂查询表达式
     * @param pageable 分页对象
     * @return org.springframework.data.domain.Page<T>
     * @author wanghaodong
     */
    Page<T> findAllByPredicateAndPage(Specification<T> spec, Pageable pageable);

    /**
     * 修改数据
     *
     * @param domain 数据实体
     * @return T 修改的数据实体
     * @author wanghaodong
     */
    @Transactional(rollbackFor = Exception.class)
    T update(T domain);

    /**
     * 保存数据
     *
     * @param domain 数据实体
     * @return T 保存的数据实体
     * @author wanghaodong
     */
    @Transactional(rollbackFor = Exception.class)
    T save(T domain);

    /**
     * 根据主键id删除数据
     *
     * @param id 主键id
     * @author wanghaodong
     */
    @Transactional(rollbackFor = Exception.class)
    void delete(ID id);

    /**
     * 根据实体删除数据
     *
     * @param domain 实体对象
     * @author wanghaodong
     */
    void delete(T domain);

    /**
     * 根据实体集合删除数据
     *
     * @param domains 实体对象集合
     * @author wanghaodong
     */
    void deleteAll(Collection<T> domains);

    /**
     * 删除所有数据
     *
     * @author wanghaodong
     */
    void deleteAll();

    /**
     * 保存集合中的数据
     *
     * @param domains 实体集合
     * @return java.util.List<T>  保存的实体集合
     * @author wanghaodong
     */
    @Transactional(rollbackFor = Exception.class)
    List<T> saveInBatch(Collection<T> domains);

    /**
     * 修改集合中的数据
     *
     * @param domains 实体集合
     * @return java.util.List<T> 修改的实体集合
     * @author wanghaodong
     */
    @Transactional(rollbackFor = Exception.class)
    List<T> updateInBatch(Collection<T> domains);

    /**
     * 删除主键id集合中的数据
     *
     * @param ids 主键id集合
     * @author wanghaodong
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByIdInBatch(Collection<ID> ids);

    /**
     * 返回数据总数
     *
     * @return long 总条数
     */
    long count();
}
