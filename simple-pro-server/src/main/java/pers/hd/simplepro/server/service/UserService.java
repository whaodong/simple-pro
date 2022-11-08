package pers.hd.simplepro.server.service;

import pers.hd.simplepro.core.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.UserDao;
import pers.hd.simplepro.server.pojo.dto.UserDTO;
import pers.hd.simplepro.server.pojo.entity.User;

/**
 * @author WangHaoDong
 */
public interface UserService extends JpaQueryDsService<User, Integer, UserDao> {
    UserDTO findByUsername(String userName);
}
