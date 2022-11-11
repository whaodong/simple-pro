package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

@Data
public class RoleQueryCriteria {

    @Query(blurry = "name,description")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}