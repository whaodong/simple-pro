package pers.hd.simplepro.server.dao;

import pers.hd.simplepro.core.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.pojo.entity.User;

/**
 * @author WangHaoDong
 */
public interface UserDao extends JpaQueryDsDao<User, Integer> {
    User findByUsername(String userName);
}
