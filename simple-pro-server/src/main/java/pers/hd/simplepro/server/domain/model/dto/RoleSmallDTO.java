package pers.hd.simplepro.server.domain.model.dto;

import lombok.Data;
import pers.hd.simplepro.server.domain.model.support.base.OutputConverter;
import pers.hd.simplepro.server.domain.model.entity.Roles;

@Data
public class RoleSmallDTO implements OutputConverter<RoleSmallDTO, Roles> {

    private String id;

    private String name;

    private Integer level;

    private String dataScope;
}
