package pers.hd.simplepro.server.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="sys_dict")
public class Dicts extends BaseEntity{

    @Id
    @Column(name = "dict_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String description;
}
