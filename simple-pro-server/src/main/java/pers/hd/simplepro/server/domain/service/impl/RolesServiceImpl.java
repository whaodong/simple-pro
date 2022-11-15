package pers.hd.simplepro.server.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.server.domain.model.dto.AuthorityDTO;
import pers.hd.simplepro.server.domain.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.domain.model.dto.RolesDTO;
import pers.hd.simplepro.server.domain.model.dto.UsersDTO;
import pers.hd.simplepro.server.domain.model.entity.Menus;
import pers.hd.simplepro.server.domain.model.entity.Roles;
import pers.hd.simplepro.server.domain.model.entity.RolesMenus;
import pers.hd.simplepro.server.domain.model.params.RolesParam;
import pers.hd.simplepro.server.domain.repository.MenusRepository;
import pers.hd.simplepro.server.domain.repository.RolesMenusRepository;
import pers.hd.simplepro.server.domain.repository.RolesRepository;
import pers.hd.simplepro.server.domain.repository.UsersRepository;
import pers.hd.simplepro.server.domain.service.RolesService;
import pers.hd.simplepro.server.domain.service.base.AbstractCrudService;
import pers.hd.simplepro.server.exception.BadRequestException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class RolesServiceImpl extends AbstractCrudService<Roles, String, RolesRepository>
        implements RolesService {
    private final RolesRepository rolesRepository;
    private final UsersRepository usersRepository;
    private final RolesMenusRepository rolesMenusDao;
    private final MenusRepository menusRepository;

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UsersDTO user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        Set<RolesDTO> roles = rolesRepository.findByUserId(user.getId()).stream().map(role -> (RolesDTO) new RolesDTO().convertFrom(role)).collect(Collectors.toSet());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .map(Menus::getPermission)
                .filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        return permissions.stream().map(AuthorityDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Set<RoleSmallDTO> findByUserId(String id) {
        return rolesRepository.findByUserId(id).stream().map(role -> (RoleSmallDTO) new RoleSmallDTO().convertFrom(role)).collect(Collectors.toSet());
    }

    @Override
    public Set<RolesDTO> findByUserId(String id, boolean hasMenus, boolean hasUsers) {
        return null;
    }

    @Override
    public Integer findByRoles(Set<RolesParam> roles) {
        if (roles.size() == 0) {
            return Integer.MAX_VALUE;
        }
        List<Roles> roleDtos = new ArrayList<>();
        for (RolesParam role : roles) {
            roleDtos.add(find(role.getId()));
        }
        return Collections.min(roleDtos.stream().map(Roles::getLevel).collect(Collectors.toList()));
    }

    @Override
    public void updateMenu(RolesParam roleParam) {
        // 更新菜单
        RolesMenus rolesMenus;
        for (Menus menu : roleParam.getMenus()) {
            rolesMenus = new RolesMenus();
            rolesMenus.setRoleId(roleParam.getId());
            rolesMenus.setMenuId(menu.getId());
            rolesMenusDao.save(rolesMenus);
        }
    }

    @Override
    public void verification(Set<String> ids) {
        if (usersRepository.countByRoles(ids) > 0) {
            throw new BadRequestException("所选角色存在用户关联，请解除关联再试！");
        }
    }
}
