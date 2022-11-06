package pers.hd.simplepro.server.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    @Override
    public void prePersist() {
        super.prePersist();
        if (email == null) {
            email = "";
        }

        if (description == null) {
            description = "";
        }
    }
}
