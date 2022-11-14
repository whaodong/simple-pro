package pers.hd.simplepro.server.domain.repository;

import org.springframework.data.jpa.repository.Query;
import pers.hd.simplepro.server.domain.repository.base.BaseRepository;
import pers.hd.simplepro.server.domain.model.entity.Roles;

import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface RolesRepository extends BaseRepository<Roles, String> {
    @Query(value = "SELECT r.* FROM sys_role r, sys_users_roles u WHERE " +
            "r.role_id = u.role_id AND u.user_id = ?1",nativeQuery = true)
    Set<Roles> findByUserId(String id);
}
