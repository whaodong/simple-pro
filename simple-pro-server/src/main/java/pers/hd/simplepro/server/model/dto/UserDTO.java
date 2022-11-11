package pers.hd.simplepro.server.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pers.hd.simplepro.server.model.entity.Roles;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.model.support.base.OutputConverter;

import java.util.Date;
import java.util.Set;

/**
 * @author WangHaoDong
 */
@Data
@ToString
@EqualsAndHashCode
public class UserDTO implements OutputConverter<UserDTO, Users> {
    private Long id;

    private String username;

    private Boolean enabled;

    private Set<Roles> roles;

    private String password;

    private String nickname;

    private String email;

    private String description;

    private Date createTime;

    private Date updateTime;

    @JSONField(serialize = false)
    private Boolean isAdmin = false;
}
