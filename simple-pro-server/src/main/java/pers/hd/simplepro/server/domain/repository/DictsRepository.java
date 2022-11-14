package pers.hd.simplepro.server.domain.repository;

import pers.hd.simplepro.server.domain.model.entity.Dicts;
import pers.hd.simplepro.server.domain.repository.base.BaseRepository;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface DictsRepository extends BaseRepository<Dicts, String> {

    long deleteByIdIn(Set<String> ids);

}
