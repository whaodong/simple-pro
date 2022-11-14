package pers.hd.simplepro.server.domain.model.dto;

import pers.hd.simplepro.server.domain.model.entity.DictDetail;
import pers.hd.simplepro.server.domain.model.support.base.OutputConverter;

/**
 * @author WangHaoDong
 */
public class DictDetailDTO implements OutputConverter<DictDetailDTO, DictDetail> {

    private String id;

    private DictsDTO dict;

    private String label;

    private String value;

    private Integer dictSort;
}
