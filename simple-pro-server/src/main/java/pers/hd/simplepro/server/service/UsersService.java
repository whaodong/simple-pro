package pers.hd.simplepro.server.service;

import pers.hd.simplepro.server.dao.UsersDao;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.model.dto.UsersDTO;
import pers.hd.simplepro.server.model.entity.Users;

/**
 * @author WangHaoDong
 */
public interface UsersService extends JpaQueryDsService<Users, Long, UsersDao> {
    UsersDTO findByUsername(String userName);

}
