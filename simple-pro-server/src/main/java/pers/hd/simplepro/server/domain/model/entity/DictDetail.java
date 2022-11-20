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
@Table(name="sys_dict_detail")
public class DictDetail extends BaseEntity {

    @Id
    @Column(name = "detail_id")
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private String id;

    private String label;

    private String value;

    private Integer dictSort = 999;
}
