package pers.hd.simplepro.server.model.dto;

import lombok.Data;
import pers.hd.simplepro.server.model.support.base.OutputConverter;
import pers.hd.simplepro.server.model.entity.Roles;

@Data
public class RoleSmallDTO implements OutputConverter<RoleSmallDTO, Roles> {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
