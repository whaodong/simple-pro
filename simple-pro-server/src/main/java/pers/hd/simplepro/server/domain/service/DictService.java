package pers.hd.simplepro.server.domain.service;

import pers.hd.simplepro.server.domain.service.base.BaseCrudService;
import pers.hd.simplepro.server.domain.repository.DictsRepository;
import pers.hd.simplepro.server.domain.model.entity.Dicts;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface DictService extends BaseCrudService<Dicts, String, DictsRepository> {
    /**
     * 删除
     * @param ids /
     */
    void delete(Set<String> ids);
}
