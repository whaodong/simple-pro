package pers.hd.simplepro.server.model.entity;

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
    private Long id;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "role_id")
    private Long roleId;
}
