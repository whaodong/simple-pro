package pers.hd.simplepro.server.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author WangHaoDong
 */
@Getter
@Setter
@Entity
@Table(name = "sys_roles_menus")
public class RolesMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "role_id")
    private String roleId;
}
