package pers.hd.simplepro.server.domain.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pers.hd.simplepro.server.domain.repository.base.BaseRepository;
import pers.hd.simplepro.server.domain.model.entity.Menus;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface MenusRepository extends BaseRepository<Menus, String> {
    boolean existsByTitle(String title);

    Menus findByTitle(String title);

    Menus findByComponentName(String componentName);

    boolean existsByComponentName(String componentName);

    List<Menus> findByPid(String pId);

    List<Menus> findByPidIsNull();

    /**
     * 根据角色ID与菜单类型查询菜单
     * @param roleIds roleIDs
     * @param type 类型
     * @return /
     */
    @Query(value = "SELECT m.* FROM sys_menu m, sys_roles_menus r WHERE " +
            "m.menu_id = r.menu_id AND r.role_id IN ?1 AND type != ?2 order by m.menu_sort asc",nativeQuery = true)
    List<Menus> findByRoleIdsAndTypeNot(Set<String> roleIds, int type);

    @Query(value = "SELECT m.* FROM sys_menu m, sys_roles_menus r WHERE " +
            "m.menu_id = r.menu_id AND r.role_id = ?1 AND type != 2 order by m.menu_sort asc",nativeQuery = true)
    List<Menus> findByRoleId(String roleId);

    /**
     * 更新节点数目
     * @param count /
     * @param menuId /
     */
    @Modifying
    @Query(value = " update sys_menu set sub_count = ?1 where menu_id = ?2 ",nativeQuery = true)
    void updateSubCntById(int count, String menuId);

    int countByPid(String pId);

    @Query(value = "SELECT * FROM sys_menu WHERE type != ?1 order by menu_sort asc",nativeQuery = true)
    LinkedHashSet<Menus> findByType(int type);

}
