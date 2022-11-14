package pers.hd.simplepro.server.domain.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author wanghaodong
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "sys_menu")
public class Menus extends BaseEntity {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;

    @Column(name = "name")
    private String componentName;

    private Integer menuSort = 999;

    private String component;

    private String path;

    private Integer type;

    private String permission;

    private String icon;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean cache;

    @Column(columnDefinition = "bit(1) default 0")
    private Boolean hidden;

    private String pid;

    private Integer subCount = 0;

    private Boolean iFrame;
}
