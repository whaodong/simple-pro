package pers.hd.simplepro.server.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author wanghaodong
 */
@Entity
@Getter
@Setter
@Table(name = "sys_menu")
public class Menus extends BaseEntity {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private Long pid;

    private Integer subCount = 0;

    private Boolean iFrame;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menus menu = (Menus) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}