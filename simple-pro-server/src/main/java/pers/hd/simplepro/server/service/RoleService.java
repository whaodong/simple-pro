package pers.hd.simplepro.server.service;

import org.springframework.security.core.GrantedAuthority;
import pers.hd.simplepro.core.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.RoleDao;
import pers.hd.simplepro.server.pojo.dto.UserDTO;
import pers.hd.simplepro.server.pojo.entity.Role;

import java.util.List;

/**
 * @author WangHaoDong
 */
public interface RoleService extends JpaQueryDsService<Role, Integer, RoleDao> {
    /**
     * 获取用户权限信息
     * @param user 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDTO user);
}
