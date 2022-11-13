package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.SimpleQuery;

@Data
public class DictQueryCriteria {

    @SimpleQuery(blurry = "name,description")
    private String blurry;
}
