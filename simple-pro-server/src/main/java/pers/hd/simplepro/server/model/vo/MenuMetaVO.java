package pers.hd.simplepro.server.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;


/**
 * @author wanghaodong
 */
@Data
@AllArgsConstructor
public class MenuMetaVO implements Serializable {

    private String title;

    private String icon;

    private Boolean noCache;
}