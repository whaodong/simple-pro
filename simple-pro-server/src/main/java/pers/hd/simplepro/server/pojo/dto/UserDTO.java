package pers.hd.simplepro.server.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pers.hd.simplepro.server.pojo.dto.base.OutputConverter;
import pers.hd.simplepro.server.pojo.entity.User;

import java.util.Date;

/**
 * @author WangHaoDong
 */
@Data
@ToString
@EqualsAndHashCode
public class UserDTO implements OutputConverter<UserDTO, User> {
    private Integer id;

    private String username;

    private Boolean enabled;

    private String password;

    private String nickname;

    private String email;

    private String description;

    private Date createTime;

    private Date updateTime;

    @JSONField(serialize = false)
    private Boolean isAdmin = false;
}
