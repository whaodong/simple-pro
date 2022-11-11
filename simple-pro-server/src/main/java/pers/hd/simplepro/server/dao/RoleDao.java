package pers.hd.simplepro.server.dao;

import org.springframework.data.jpa.repository.Query;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.model.entity.Roles;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface RoleDao extends JpaQueryDsDao<Roles, Long> {
    @Query(value = "SELECT r.* FROM sys_role r, sys_users_roles u WHERE " +
            "r.role_id = u.role_id AND u.user_id = ?1",nativeQuery = true)
    Set<Roles> findByUserId(Long id);
}
