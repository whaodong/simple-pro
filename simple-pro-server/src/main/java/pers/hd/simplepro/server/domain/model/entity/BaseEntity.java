package pers.hd.simplepro.server.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import pers.hd.simplepro.server.util.DateUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 通用实体定义
 *
 * @author WangHaoDong
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column(name = "create_by", updatable = false)
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @PrePersist
    protected void prePersist() {
        Date now = DateUtils.now();
        if (createTime == null) {
            createTime = now;
        }

        if (updateTime == null) {
            updateTime = now;
        }
    }

    @PreUpdate
    protected void preUpdate() {
        updateTime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        updateTime = new Date();
    }

}
