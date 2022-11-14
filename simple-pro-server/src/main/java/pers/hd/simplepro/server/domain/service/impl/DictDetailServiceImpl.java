package pers.hd.simplepro.server.domain.service.impl;

import org.springframework.stereotype.Service;
import pers.hd.simplepro.server.domain.service.base.AbstractCrudService;
import pers.hd.simplepro.server.domain.repository.DictDetailRepository;
import pers.hd.simplepro.server.domain.model.entity.DictDetail;
import pers.hd.simplepro.server.domain.service.DictDetailService;

/**
 * @author WangHaoDong
 */
@Service
public class DictDetailServiceImpl extends AbstractCrudService<DictDetail, String, DictDetailRepository>
        implements DictDetailService {
}
