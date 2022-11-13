package pers.hd.simplepro.server.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.SimpleQuery;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.model.support.base.InputConverter;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author WangHaoDong
 */
@Data
public class UserQueryCriteria implements InputConverter<Users> {
    @SimpleQuery
    private Long id;

    @SimpleQuery(blurry = "email,userName,nickName")
    private String blurry;

    @SimpleQuery
    private Boolean enabled;

    @SimpleQuery(type = SimpleQuery.Type.BETWEEN)
    private List<Timestamp> createTime;
}
