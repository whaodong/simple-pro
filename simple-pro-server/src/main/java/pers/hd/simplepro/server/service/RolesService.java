package pers.hd.simplepro.server.service;

import org.springframework.security.core.GrantedAuthority;
import pers.hd.simplepro.server.dao.RolesDao;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.model.dto.UsersDTO;
import pers.hd.simplepro.server.model.entity.Roles;
import pers.hd.simplepro.server.model.params.RolesParam;

import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface RolesService extends JpaQueryDsService<Roles, Long, RolesDao> {
    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UsersDTO user);

    Set<RoleSmallDTO> findByUserId(Long id);

    Integer findByRoles(Set<RolesParam> roles);

    void  updateMenu(RolesParam roleParam);

    void verification(Set<Long> ids);
}
