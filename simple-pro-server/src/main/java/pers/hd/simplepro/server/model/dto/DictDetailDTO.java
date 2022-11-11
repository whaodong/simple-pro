package pers.hd.simplepro.server.model.dto;

import pers.hd.simplepro.server.model.support.base.OutputConverter;
import pers.hd.simplepro.server.model.entity.Dict;
import pers.hd.simplepro.server.model.entity.DictDetail;

/**
 * @author WangHaoDong
 */
public class DictDetailDTO implements OutputConverter<DictDetailDTO, DictDetail> {

    private Long id;

    private Dict dict;

    private String label;

    private String value;

    private Integer dictSort;
}
