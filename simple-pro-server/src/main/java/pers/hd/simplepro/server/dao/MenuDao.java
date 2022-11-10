package pers.hd.simplepro.server.dao;

import pers.hd.simplepro.core.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.pojo.entity.Menu;

import java.util.List;

/**
 * @author WangHaoDong
 */
public interface MenuDao extends JpaQueryDsDao<Menu, Integer> {
    List<Menu> findByTitle(String title);

}
