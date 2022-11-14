package pers.hd.simplepro.server.domain.model.query;

import lombok.Data;
import pers.hd.simplepro.server.annotation.SimpleQuery;
import pers.hd.simplepro.server.domain.model.entity.Users;
import pers.hd.simplepro.server.domain.model.support.base.InputConverter;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author WangHaoDong
 */
@Data
public class UserQueryCriteria implements InputConverter<Users> {

    @SimpleQuery
    private String id;

    @SimpleQuery(blurry = "email,nickName")
    private String blurry;

    @SimpleQuery
    private String userName;

    @SimpleQuery
    private Boolean enabled;

    @SimpleQuery(type = SimpleQuery.Type.BETWEEN)
    private List<Timestamp> createTime;
}
