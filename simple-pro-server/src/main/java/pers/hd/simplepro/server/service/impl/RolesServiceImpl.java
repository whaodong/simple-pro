package pers.hd.simplepro.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.core.exception.BadRequestException;
import pers.hd.simplepro.server.dao.MenusDao;
import pers.hd.simplepro.server.dao.RolesDao;
import pers.hd.simplepro.server.dao.RolesMenusDao;
import pers.hd.simplepro.server.dao.UsersDao;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.model.dto.UsersDTO;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.entity.Roles;
import pers.hd.simplepro.server.model.entity.RolesMenus;
import pers.hd.simplepro.server.model.params.RolesParam;
import pers.hd.simplepro.server.service.RolesService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class RolesServiceImpl extends JpaQueryDsServiceImpl<Roles, Long, RolesDao>
        implements RolesService {
    private final RolesDao roleDao;
    private final UsersDao userDao;
    private final RolesMenusDao rolesMenusDao;
    private final MenusDao menuDao;

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UsersDTO user) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        // todo 构建权限
        return null;
    }

    @Override
    public Set<RoleSmallDTO> findByUserId(Long id) {
        return roleDao.findByUserId(id).stream().map(role -> (RoleSmallDTO) new RoleSmallDTO().convertFrom(role)).collect(Collectors.toSet());
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
    public void verification(Set<Long> ids) {
        if (userDao.countByRoles(ids) > 0) {
            throw new BadRequestException("所选角色存在用户关联，请解除关联再试！");
        }
    }
}
