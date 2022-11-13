package pers.hd.simplepro.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.dao.DictsDao;
import pers.hd.simplepro.server.model.entity.Dicts;
import pers.hd.simplepro.server.service.DictService;
import pers.hd.simplepro.server.util.RedisUtils;

import java.util.Set;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends JpaQueryDsServiceImpl<Dicts, Long, DictsDao>
        implements DictService {

    private final DictsDao dictDao;
    private final RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        dictDao.deleteByIdIn(ids);
    }
}
