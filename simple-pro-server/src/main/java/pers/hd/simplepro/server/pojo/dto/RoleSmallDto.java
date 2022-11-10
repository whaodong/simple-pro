package pers.hd.simplepro.server.pojo.dto;

import lombok.Data;
import pers.hd.simplepro.server.pojo.dto.base.OutputConverter;
import pers.hd.simplepro.server.pojo.entity.Role;

@Data
public class RoleSmallDto implements OutputConverter<RoleSmallDto, Role> {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
