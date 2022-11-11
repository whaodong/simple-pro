package pers.hd.simplepro.server.model.dto;

import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.model.support.base.OutputConverter;
import pers.hd.simplepro.server.model.entity.Roles;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class RoleDTO implements OutputConverter<RoleDTO, Roles> {

    private Long id;

    private Set<Users> users;

    private Set<Menus> menus;

    private String name;

    private String dataScope;

    private Integer level;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleDTO roleDto = (RoleDTO) o;
        return Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}