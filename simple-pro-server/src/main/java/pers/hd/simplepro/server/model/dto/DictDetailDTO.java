package pers.hd.simplepro.server.model.dto;

import pers.hd.simplepro.server.model.entity.DictDetail;
import pers.hd.simplepro.server.model.support.base.OutputConverter;

/**
 * @author WangHaoDong
 */
public class DictDetailDTO implements OutputConverter<DictDetailDTO, DictDetail> {

    private Long id;

    private DictsDTO dict;

    private String label;

    private String value;

    private Integer dictSort;
}
