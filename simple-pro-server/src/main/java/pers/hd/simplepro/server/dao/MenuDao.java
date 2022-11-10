package pers.hd.simplepro.server.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pers.hd.simplepro.core.jpa.base.JpaQueryDsDao;
import pers.hd.simplepro.server.pojo.entity.Menu;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface MenuDao extends JpaQueryDsDao<Menu, Long> {
    boolean existsByTitle(String title);

    Menu findByTitle(String title);

    Menu findByComponentName(String componentName);

    boolean existsByComponentName(String componentName);

    List<Menu> findByPid(long pId);

    List<Menu> findByPidIsNull();

    /**
     * 根据角色ID与菜单类型查询菜单
     * @param roleIds roleIDs
     * @param type 类型
     * @return /
     */
    @Query(value = "SELECT m.* FROM sys_menu m, sys_roles_menus r WHERE " +
            "m.menu_id = r.menu_id AND r.role_id IN ?1 AND type != ?2 order by m.menu_sort asc",nativeQuery = true)
    LinkedHashSet<Menu> findByRoleIdsAndTypeNot(Set<Long> roleIds, int type);

    /**
     * 更新节点数目
     * @param count /
     * @param menuId /
     */
    @Modifying
    @Query(value = " update sys_menu set sub_count = ?1 where menu_id = ?2 ",nativeQuery = true)
    void updateSubCntById(int count, Long menuId);

    int countByPid(long pId);


}
