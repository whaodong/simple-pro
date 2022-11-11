package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.Query;

@Data
public class DictQueryCriteria {

    @Query(blurry = "name,description")
    private String blurry;
}