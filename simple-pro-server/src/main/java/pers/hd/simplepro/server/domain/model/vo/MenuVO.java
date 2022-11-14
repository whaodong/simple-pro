package pers.hd.simplepro.server.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 构建前端路由时用到
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVO implements Serializable {

    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVO meta;

    private List<MenuVO> children;
}