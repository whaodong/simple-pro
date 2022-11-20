package pers.hd.simplepro.server.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author WangHaoDong
 */
@Getter
@Setter
@Entity
@Table(name = "sys_roles_menus")
public class RolesMenus {

    @Id
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "role_id")
    private String roleId;
}
