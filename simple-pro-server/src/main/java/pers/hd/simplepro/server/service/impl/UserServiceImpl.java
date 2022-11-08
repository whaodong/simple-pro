package pers.hd.simplepro.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.core.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.dao.UserDao;
import pers.hd.simplepro.server.pojo.dto.UserDTO;
import pers.hd.simplepro.server.pojo.entity.User;
import pers.hd.simplepro.server.service.UserService;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends JpaQueryDsServiceImpl<User, Integer, UserDao>
        implements UserService {
    private final UserDao userDao;

    @Override
    public UserDTO findByUsername(String userName) {
        User user = userDao.findByUsername(userName);
        if (user != null) {
            return new UserDTO().convertFrom(user);
        }
        return null;
    }
}
