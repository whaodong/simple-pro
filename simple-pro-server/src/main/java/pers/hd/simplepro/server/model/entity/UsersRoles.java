package pers.hd.simplepro.server.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author WangHaoDong
 */
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "role_id")
    private Long roleId;
}
