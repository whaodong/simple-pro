package pers.hd.simplepro.server.domain.model.dto;

import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.domain.model.entity.Menus;
import pers.hd.simplepro.server.domain.model.entity.Roles;
import pers.hd.simplepro.server.domain.model.entity.Users;
import pers.hd.simplepro.server.domain.model.support.base.OutputConverter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class RolesDTO implements OutputConverter<RolesDTO, Roles> {

    private String id;

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
        RolesDTO roleDto = (RolesDTO) o;
        return Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}