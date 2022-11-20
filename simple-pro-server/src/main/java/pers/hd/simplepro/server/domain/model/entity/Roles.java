package pers.hd.simplepro.server.domain.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pers.hd.simplepro.server.enums.DataScopeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private String id;

    @NotBlank
    private String name;

    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    @Column(name = "level")
    private Integer level = 3;

    private String description;
}
