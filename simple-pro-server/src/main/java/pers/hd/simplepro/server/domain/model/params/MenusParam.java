package pers.hd.simplepro.server.domain.model.params;


import lombok.Data;
import pers.hd.simplepro.server.domain.model.entity.Menus;
import pers.hd.simplepro.server.domain.model.support.base.InputConverter;

/**
 * @author wanghaodong
 */
@Data
public class MenusParam implements InputConverter<Menus> {
    private String id;

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

    private String pid;

    private Integer subCount = 0;

    private Boolean iFrame;
}
