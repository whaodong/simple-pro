package pers.hd.simplepro.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.core.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.dao.RoleDao;
import pers.hd.simplepro.server.pojo.dto.RoleSmallDto;
import pers.hd.simplepro.server.pojo.dto.UserDTO;
import pers.hd.simplepro.server.pojo.entity.Role;
import pers.hd.simplepro.server.service.RoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends JpaQueryDsServiceImpl<Role, Integer, RoleDao>
        implements RoleService {
    private final RoleDao roleDao;

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
    public Set<RoleSmallDto> findByUserId(Long id) {
        return roleDao.findByUserId(id).stream().map(role -> (RoleSmallDto)new RoleSmallDto().convertFrom(role)).collect(Collectors.toSet());
    }
}
