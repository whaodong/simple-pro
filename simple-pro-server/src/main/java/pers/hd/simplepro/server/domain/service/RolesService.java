package pers.hd.simplepro.server.domain.service;

import org.springframework.security.core.GrantedAuthority;
import pers.hd.simplepro.server.domain.model.dto.RolesDTO;
import pers.hd.simplepro.server.domain.repository.RolesRepository;
import pers.hd.simplepro.server.domain.service.base.BaseCrudService;
import pers.hd.simplepro.server.domain.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.domain.model.dto.UsersDTO;
import pers.hd.simplepro.server.domain.model.entity.Roles;
import pers.hd.simplepro.server.domain.model.params.RolesParam;

import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface RolesService extends BaseCrudService<Roles, String, RolesRepository> {
    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UsersDTO user);

    Set<RoleSmallDTO> findByUserId(String id);

    Set<RolesDTO> findByUserId(String id, boolean hasMenus, boolean hasUsers);

    Integer findByRoles(Set<RolesParam> roles);

    void updateMenu(RolesParam roleParam);

    void verification(Set<String> ids);
}
