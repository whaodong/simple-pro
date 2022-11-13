package pers.hd.simplepro.server.model.params;


import lombok.Data;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.support.base.InputConverter;

/**
 * @author wanghaodong
 */
@Data
public class MenusParam implements InputConverter<Menus> {
    private Long id;

    private String title;

    private String componentName;

    private Integer menuSort = 999;

    private String component;

    private String path;

    private Integer type;

    private String permission;

    private String icon;

    private Boolean cache;

    private Boolean hidden;

    private Long pid;

    private Integer subCount = 0;

    private Boolean iFrame;
}
