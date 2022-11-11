package pers.hd.simplepro.server.model.params;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.entity.Users;

import java.util.Set;

/**
 * @author Administrator
 */
@Getter
@Setter
@EqualsAndHashCode
public class RoleParam {

    private Long id;

    private String name;

    private Integer level;

    private String description;

    private Set<Users> users;

    private Set<Menus> menus;

}
