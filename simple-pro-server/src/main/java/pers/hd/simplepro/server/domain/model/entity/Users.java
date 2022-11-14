package pers.hd.simplepro.server.domain.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * User entity
 *
 * @author WangHaoDong
 */
@Data
@Entity
@Table(name = "users")
@ToString(callSuper = true)
public class Users extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @NotBlank
    @Column(name = "username",unique = true)
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    private String gender;

    private String password;

    private Boolean enabled;

    private Boolean isAdmin = false;

    @Column(name = "pwd_reset_time")
    private Date pwdResetTime;

}
