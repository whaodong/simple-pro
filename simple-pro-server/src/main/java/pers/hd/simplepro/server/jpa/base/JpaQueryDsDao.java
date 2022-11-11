package pers.hd.simplepro.server.jpa.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * JPA持久层定义
 *
 * @author WangHaoDong
 */
@NoRepositoryBean
public interface JpaQueryDsDao<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {

}
