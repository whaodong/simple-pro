package pers.hd.simplepro.server.domain.repository;

import pers.hd.simplepro.server.domain.repository.base.BaseRepository;
import pers.hd.simplepro.server.domain.model.entity.DictDetail;

import java.util.List;

/**
 * @author WangHaoDong
 */
public interface DictDetailRepository extends BaseRepository<DictDetail, String> {

    List<DictDetail> findByLabel(String name);
}
