package pers.hd.simplepro.server.model.dto;

import pers.hd.simplepro.server.model.support.base.OutputConverter;
import pers.hd.simplepro.server.model.entity.Dict;
import pers.hd.simplepro.server.model.entity.DictDetail;

import java.util.List;

/**
 * @author WangHaoDong
 */
public class DictDTO implements OutputConverter<DictDTO, Dict> {
    private Long id;

    private List<DictDetail> dictDetails;

    private String name;

    private String description;
}
