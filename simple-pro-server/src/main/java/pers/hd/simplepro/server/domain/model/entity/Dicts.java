package pers.hd.simplepro.server.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="sys_dict")
public class Dicts extends BaseEntity{

    @Id
    @Column(name = "dict_id")
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private String id;

    private String name;

    private String description;
}
