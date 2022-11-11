package pers.hd.simplepro.server.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="sys_dict")
public class Dict extends BaseEntity{

    @Id
    @Column(name = "dict_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}