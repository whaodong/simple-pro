package pers.hd.simplepro.server.domain.model.dto;

import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.domain.model.entity.Menus;
import pers.hd.simplepro.server.domain.model.support.base.OutputConverter;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
public class MenuDTO implements OutputConverter<MenuDTO, Menus> {

    private String id;

    private List<MenuDTO> children;

    private Integer type;

    private String permission;

    private String title;

    private Integer menuSort;

    private String path;

    private String component;

    private String pid;

    private Integer subCount;

    private Boolean iFrame;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private String icon;

    private Date createTime;

    public Boolean getHasChildren() {
        return subCount > 0;
    }

    public Boolean getLeaf() {
        return subCount <= 0;
    }

    public String getLabel() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuDTO menuDto = (MenuDTO) o;
        return Objects.equals(id, menuDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
