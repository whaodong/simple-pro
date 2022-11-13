package pers.hd.simplepro.server.dao;

import pers.hd.simplepro.server.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.model.entity.Dicts;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface DictsDao extends JpaQueryDsDao<Dicts, Long> {

    long deleteByIdIn(Set<Long> ids);

}
