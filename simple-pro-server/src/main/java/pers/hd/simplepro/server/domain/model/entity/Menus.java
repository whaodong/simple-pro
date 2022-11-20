package pers.hd.simplepro.server.domain.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
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
