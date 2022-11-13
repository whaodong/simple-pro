package pers.hd.simplepro.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.hd.simplepro.server.dao.UsersDao;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.model.dto.UsersDTO;
import pers.hd.simplepro.server.model.entity.Users;
import pers.hd.simplepro.server.service.UsersService;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl extends JpaQueryDsServiceImpl<Users, Long, UsersDao>
        implements UsersService {
    private final UsersDao userDao;

    @Override
    public UsersDTO findByUsername(String userName) {
        Users user = userDao.findByUserName(userName);
        if (user != null) {
            return new UsersDTO().convertFrom(user);
        }
        return null;
    }
}
