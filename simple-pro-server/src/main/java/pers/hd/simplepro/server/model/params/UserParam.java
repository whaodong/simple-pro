package pers.hd.simplepro.server.model.params;

import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.model.support.base.InputConverter;

import java.util.Set;

/**
 * @author WangHaoDong
 */
@Getter
@Setter
public class UserParam implements InputConverter<Users> {

    private Long id;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String description;

    private Boolean enabled;

    private Set<RoleParam> roles;

}
