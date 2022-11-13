package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.SimpleQuery;

@Data
public class DictDetailQueryCriteria {

    @SimpleQuery(propName = "name",joinName = "dicts")
    private String dictName;
}
