package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.SimpleQuery;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MenuQueryCriteria {

    @SimpleQuery(blurry = "title,component,permission")
    private String blurry;

    @SimpleQuery(type = SimpleQuery.Type.BETWEEN)
    private List<Timestamp> createTime;

    @SimpleQuery(type = SimpleQuery.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    @SimpleQuery
    private Long pid;
}
