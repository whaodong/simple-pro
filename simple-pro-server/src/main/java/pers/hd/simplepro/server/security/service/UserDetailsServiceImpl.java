package pers.hd.simplepro.server.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.core.exception.BadRequestException;
import pers.hd.simplepro.server.model.dto.UsersDTO;
import pers.hd.simplepro.server.security.model.LoginProperties;
import pers.hd.simplepro.server.security.service.dto.JwtUserDto;
import pers.hd.simplepro.server.service.RolesService;
import pers.hd.simplepro.server.service.UsersService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersService userService;
    private final RolesService roleService;
    private final LoginProperties loginProperties;

    public void setEnableCache(boolean enableCache) {
        this.loginProperties.setCacheEnable(enableCache);
    }

    /**
     * 用户信息缓存
     *
     * @see {@link UserCacheClean}
     */
    static Map<String, JwtUserDto> userDtoCache = new ConcurrentHashMap<>();

    @Override
    public JwtUserDto loadUserByUsername(String username) {
        JwtUserDto jwtUserDto = null;
        UsersDTO user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "账号不存在");
        } else {
            if (!user.getEnabled()) {
                throw new BadRequestException("账号未激活！");
            }
            jwtUserDto = new JwtUserDto(
                    user,
                    roleService.mapToGrantedAuthorities(user)
            );
            userDtoCache.put(username, jwtUserDto);
        }
        return jwtUserDto;
    }
}
