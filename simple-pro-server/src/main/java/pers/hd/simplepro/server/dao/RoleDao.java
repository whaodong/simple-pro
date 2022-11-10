package pers.hd.simplepro.server.dao;

import org.springframework.data.jpa.repository.Query;
import pers.hd.simplepro.core.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.pojo.entity.Role;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface RoleDao extends JpaQueryDsDao<Role, Integer> {
    @Query(value = "SELECT r.* FROM sys_role r, sys_users_roles u WHERE " +
            "r.role_id = u.role_id AND u.user_id = ?1",nativeQuery = true)
    Set<Role> findByUserId(Long id);
}
