package pers.hd.simplepro.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.core.exception.BadRequestException;
import pers.hd.simplepro.server.dao.MenuDao;
import pers.hd.simplepro.server.dao.RoleDao;
import pers.hd.simplepro.server.dao.UserDao;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.model.dto.RoleDTO;
import pers.hd.simplepro.server.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.model.dto.UserDTO;
import pers.hd.simplepro.server.model.entity.Roles;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.model.params.RoleParam;
import pers.hd.simplepro.server.service.RolesService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class RolesServiceImpl extends JpaQueryDsServiceImpl<Roles, Long, RoleDao>
        implements RolesService {
    private final RoleDao roleDao;
    private final UserDao userDao;

    private final MenuDao menuDao;

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDTO user) {
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
    public Integer findByRoles(Set<Roles> roles) {
        if (roles.size() == 0) {
            return Integer.MAX_VALUE;
        }
        Set<RoleDTO> roleDtos = new HashSet<>();
        for (Roles role : roles) {
            roleDtos.add(new RoleDTO().convertFrom(find(role.getId())));
        }
        return Collections.min(roleDtos.stream().map(RoleDTO::getLevel).collect(Collectors.toList()));
    }

    @Override
    public void updateMenu(RoleParam roleParam, Roles role) {
        List<Users> users = userDao.findByRoleId(role.getId());
        // 更新菜单
        role.setMenus(roleParam.getMenus());
        delCaches(resources.getId(), users);
        roleRepository.save(role);
    }

    @Override
    public void verification(Set<Long> ids) {
        if (userDao.countByRoles(ids) > 0) {
            throw new BadRequestException("所选角色存在用户关联，请解除关联再试！");
        }
    }
}
