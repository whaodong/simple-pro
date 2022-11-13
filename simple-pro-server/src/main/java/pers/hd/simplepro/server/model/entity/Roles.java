package pers.hd.simplepro.server.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.enums.DataScopeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Administrator
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "sys_role")
public class Roles extends BaseEntity {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    @Column(name = "level")
    private Integer level = 3;

    private String description;
}
