package pers.hd.simplepro.server.domain.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.SimpleQuery;

import java.sql.Timestamp;
import java.util.List;

@Data
public class RoleQueryCriteria {

    @SimpleQuery(blurry = "name,description")
    private String blurry;

    @SimpleQuery(type = SimpleQuery.Type.BETWEEN)
    private List<Timestamp> createTime;
}
