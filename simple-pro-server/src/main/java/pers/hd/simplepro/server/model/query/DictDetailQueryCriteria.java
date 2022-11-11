package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.Query;

@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name",joinName = "dict")
    private String dictName;
}