package pers.hd.simplepro.server.service;

import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.DictDao;
import pers.hd.simplepro.server.model.entity.Dict;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface DictService extends JpaQueryDsService<Dict, Long, DictDao> {
    /**
     * 删除
     * @param ids /
     */
    void delete(Set<Long> ids);
}
