package pers.hd.simplepro.server.service.impl;

import org.springframework.stereotype.Service;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.dao.DictDetailDao;
import pers.hd.simplepro.server.model.entity.DictDetail;
import pers.hd.simplepro.server.service.DictDetailService;

/**
 * @author WangHaoDong
 */
@Service
public class DictDetailServiceImpl extends JpaQueryDsServiceImpl<DictDetail, Long, DictDetailDao>
        implements DictDetailService {
}
