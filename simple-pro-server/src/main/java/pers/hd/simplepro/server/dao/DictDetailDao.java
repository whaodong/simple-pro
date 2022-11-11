package pers.hd.simplepro.server.dao;

import pers.hd.simplepro.server.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.model.entity.DictDetail;

import java.util.List;

/**
 * @author WangHaoDong
 */
public interface DictDetailDao extends JpaQueryDsDao<DictDetail, Long> {

    List<DictDetail> findByLabel(String name);
}
