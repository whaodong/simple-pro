package pers.hd.simplepro.server.domain.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.SimpleQuery;

@Data
public class DictDetailQueryCriteria {

    @SimpleQuery(propName = "name")
    private String dictName;
}
