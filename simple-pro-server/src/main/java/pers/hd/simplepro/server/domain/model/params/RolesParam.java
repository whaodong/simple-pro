package pers.hd.simplepro.server.domain.model.params;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.domain.model.entity.Menus;
import pers.hd.simplepro.server.domain.model.entity.Users;
import pers.hd.simplepro.server.domain.model.support.base.InputConverter;
import pers.hd.simplepro.server.domain.model.entity.Roles;

import java.util.Set;

/**
 * @author Administrator
 */
@Getter
@Setter
@EqualsAndHashCode
public class RolesParam implements InputConverter<Roles> {

    private String id;

    private String name;

    private Integer level;

    private String description;

    private Set<Users> users;

    private Set<Menus> menus;

}
