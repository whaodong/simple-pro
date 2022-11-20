package pers.hd.simplepro.server.domain.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pers.hd.simplepro.server.domain.repository.base.BaseRepository;
import pers.hd.simplepro.server.domain.model.entity.Users;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface UsersRepository extends BaseRepository<Users, String> {
    Users findByUserName(String userName);

    /**
     * 修改密码
     * @param username 用户名
     * @param pass 密码
     * @param lastPasswordResetTime /
     */
    @Modifying
    @Query(value = "update users set password = ?2 , pwd_reset_time = ?3 where username = ?1",nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 根据角色查询用户
     * @param roleId /
     * @return /
     */
    @Query(value = "SELECT u.* FROM users u, sys_users_roles r WHERE u.id = r.user_id AND r.role_id = ?1", nativeQuery = true)
    List<Users> findByRoleId(String roleId);

    /**
     * 根据岗位查询
     * @param ids /
     * @return /
     */
    @Query(value = "SELECT count(1) FROM users u, sys_users_jobs j WHERE u.id = j.user_id AND j.job_id IN ?1", nativeQuery = true)
    int countByJobs(Set<String> ids);

    /**
     * 根据部门查询
     * @param deptIds /
     * @return /
     */
    @Query(value = "SELECT count(1) FROM users u WHERE u.dept_id IN ?1", nativeQuery = true)
    int countByDepts(Set<String> deptIds);

    /**
     * 根据角色查询
     * @param ids /
     * @return /
     */
    @Query(value = "SELECT count(1) FROM users u, sys_users_roles r WHERE " +
            "u.user_id = r.user_id AND r.role_id in ?1", nativeQuery = true)
    int countByRoles(Set<String> ids);
}
