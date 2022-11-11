package pers.hd.simplepro.server.service;

import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.DictDetailDao;
import pers.hd.simplepro.server.model.entity.DictDetail;

/**
 * @author WangHaoDong
 */
public interface DictDetailService extends JpaQueryDsService<DictDetail, Long, DictDetailDao> {
}
