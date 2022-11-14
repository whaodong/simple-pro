package pers.hd.simplepro.server.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.hd.simplepro.server.domain.service.DictService;
import pers.hd.simplepro.server.domain.service.base.AbstractCrudService;
import pers.hd.simplepro.server.domain.repository.DictsRepository;
import pers.hd.simplepro.server.domain.model.entity.Dicts;
import pers.hd.simplepro.server.util.RedisUtils;

import java.util.Set;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends AbstractCrudService<Dicts, String, DictsRepository>
        implements DictService {

    private final DictsRepository dictsRepository;
    private final RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        dictsRepository.deleteByIdIn(ids);
    }
}
