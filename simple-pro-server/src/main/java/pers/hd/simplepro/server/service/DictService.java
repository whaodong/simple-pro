package pers.hd.simplepro.server.service;

import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.DictsDao;
import pers.hd.simplepro.server.model.entity.Dicts;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface DictService extends JpaQueryDsService<Dicts, Long, DictsDao> {
    /**
     * 删除
     * @param ids /
     */
    void delete(Set<Long> ids);
}
