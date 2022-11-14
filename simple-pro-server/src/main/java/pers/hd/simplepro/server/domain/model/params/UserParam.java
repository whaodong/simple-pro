package pers.hd.simplepro.server.domain.model.params;

import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.domain.model.entity.Users;
import pers.hd.simplepro.server.domain.model.support.base.InputConverter;

import java.util.Set;

/**
 * @author WangHaoDong
 */
@Getter
@Setter
public class UserParam implements InputConverter<Users> {

    private String id;

    private String userName;

    private String nickName;

    private String password;

    private String email;

    private String description;

    private Boolean enabled;

    private Set<RolesParam> roles;

}
