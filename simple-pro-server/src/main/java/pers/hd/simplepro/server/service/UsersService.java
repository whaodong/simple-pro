package pers.hd.simplepro.server.service;

import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.UserDao;
import pers.hd.simplepro.server.model.dto.UserDTO;
import pers.hd.simplepro.server.model.entity.Users;

/**
 * @author WangHaoDong
 */
public interface UsersService extends JpaQueryDsService<Users, Long, UserDao> {
    UserDTO findByUsername(String userName);
}
