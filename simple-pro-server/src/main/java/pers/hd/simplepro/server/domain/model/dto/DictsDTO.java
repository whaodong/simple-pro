package pers.hd.simplepro.server.domain.model.dto;

import pers.hd.simplepro.server.domain.model.entity.DictDetail;
import pers.hd.simplepro.server.domain.model.entity.Dicts;
import pers.hd.simplepro.server.domain.model.support.base.OutputConverter;

import java.util.List;

/**
 * @author WangHaoDong
 */
public class DictsDTO implements OutputConverter<DictsDTO, Dicts> {
    private String id;

    private List<DictDetail> dictDetails;

    private String name;

    private String description;
}
