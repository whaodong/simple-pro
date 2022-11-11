package pers.hd.simplepro.server.service;

import org.springframework.security.core.GrantedAuthority;
import pers.hd.simplepro.server.dao.RoleDao;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.model.dto.UserDTO;
import pers.hd.simplepro.server.model.entity.Roles;
import pers.hd.simplepro.server.model.params.RoleParam;

import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface RolesService extends JpaQueryDsService<Roles, Long, RoleDao> {
    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDTO user);

    Set<RoleSmallDTO> findByUserId(Long id);

    Integer findByRoles(Set<Roles> roles);

    void  updateMenu(RoleParam roleParam, Roles role);

    void verification(Set<Long> ids);
}
