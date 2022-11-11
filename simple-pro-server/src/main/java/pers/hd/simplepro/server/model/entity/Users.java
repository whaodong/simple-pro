package pers.hd.simplepro.server.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", length = 127)
    private String email;

    @Column(name = "description", length = 1023)
    private String description;

    @Column(name = "enabled")
    private Boolean enabled;

    private Boolean ifAdmin = false;
}
