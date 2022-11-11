package pers.hd.simplepro.server.dao;

import pers.hd.simplepro.server.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.model.entity.Dict;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface DictDao extends JpaQueryDsDao<Dict, Long> {

    long deleteByIdIn(Set<Long> ids);

}
