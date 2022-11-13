package pers.hd.simplepro.server.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.model.entity.Users;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface UsersDao extends JpaQueryDsDao<Users, Long> {
    Users findByUserName(String userName);

    /**
     * 修改密码
     * @param username 用户名
     * @param pass 密码
     * @param lastPasswordResetTime /
     */
    @Modifying
    @Query(value = "update sys_user set password = ?2 , pwd_reset_time = ?3 where username = ?1",nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 根据角色查询用户
     * @param roleId /
     * @return /
     */
    @Query(value = "SELECT u.* FROM sys_user u, sys_users_roles r WHERE" +
            " u.user_id = r.user_id AND r.role_id = ?1", nativeQuery = true)
    List<Users> findByRoleId(Long roleId);

    /**
     * 根据岗位查询
     * @param ids /
     * @return /
     */
    @Query(value = "SELECT count(1) FROM sys_user u, sys_users_jobs j WHERE u.user_id = j.user_id AND j.job_id IN ?1", nativeQuery = true)
    int countByJobs(Set<Long> ids);

    /**
     * 根据部门查询
     * @param deptIds /
     * @return /
     */
    @Query(value = "SELECT count(1) FROM sys_user u WHERE u.dept_id IN ?1", nativeQuery = true)
    int countByDepts(Set<Long> deptIds);

    /**
     * 根据角色查询
     * @param ids /
     * @return /
     */
    @Query(value = "SELECT count(1) FROM sys_user u, sys_users_roles r WHERE " +
            "u.user_id = r.user_id AND r.role_id in ?1", nativeQuery = true)
    int countByRoles(Set<Long> ids);
}
