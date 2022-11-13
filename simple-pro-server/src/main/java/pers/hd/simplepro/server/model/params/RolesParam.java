package pers.hd.simplepro.server.model.params;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.entity.Roles;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.model.support.base.InputConverter;

import java.util.Set;

/**
 * @author Administrator
 */
@Getter
@Setter
@EqualsAndHashCode
public class RolesParam implements InputConverter<Roles> {

    private Long id;

    private String name;

    private Integer level;

    private String description;

    private Set<Users> users;

    private Set<Menus> menus;

}
