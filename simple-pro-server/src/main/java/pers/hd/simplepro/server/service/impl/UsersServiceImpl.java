package pers.hd.simplepro.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.dao.UserDao;
import pers.hd.simplepro.server.model.dto.UserDTO;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.service.UsersService;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl extends JpaQueryDsServiceImpl<Users, Long, UserDao>
        implements UsersService {
    private final UserDao userDao;

    @Override
    public UserDTO findByUsername(String userName) {
        Users user = userDao.findByUsername(userName);
        if (user != null) {
            return new UserDTO().convertFrom(user);
        }
        return null;
    }
}
