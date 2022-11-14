package pers.hd.simplepro.server.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="sys_dict_detail")
public class DictDetail extends BaseEntity {

    @Id
    @Column(name = "detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String label;

    private String value;

    private Integer dictSort = 999;
}