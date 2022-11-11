package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MenuQueryCriteria {

    @Query(blurry = "title,component,permission")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    @Query
    private Long pid;
}