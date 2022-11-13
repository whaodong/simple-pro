package pers.hd.simplepro.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.MenusDao;
import pers.hd.simplepro.server.model.dto.MenuDTO;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.params.MenusParam;
import pers.hd.simplepro.server.model.query.MenuQueryCriteria;

import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface MenusService extends JpaQueryDsService<Menus, Long, MenusDao> {
    /**
     * 查询全部数据
     *
     * @param criteria 条件
     * @param isQuery  /
     * @return /
     * @throws Exception /
     */
    Page<Menus> queryAll(MenuQueryCriteria criteria, Boolean isQuery, Pageable pageable) throws Exception;

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    MenuDTO findById(long id);

    /**
     * 创建
     *
     * @param resources /
     */
    void createMenus(MenusParam resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void updateMenus(MenusParam resources);

    /**
     * 获取所有子节点，包含自身ID
     *
     * @param menuList /
     * @param menuSet  /
     * @return /
     */
    Set<Menus> getChildMenus(List<Menus> menuList, Set<Menus> menuSet);

    /**
     * 构建菜单树
     *
     * @param menuDtos 原始数据
     * @return /
     */
    List<MenuDTO> buildTree(List<MenuDTO> menuDtos);

    /**
     * 构建菜单树
     *
     * @param menuDtos /
     * @return /
     */
    Object buildMenus(List<MenuDTO> menuDtos);

    /**
     * 删除
     *
     * @param menuSet /
     */
    void delete(Set<Menus> menuSet);

    /**
     * 懒加载菜单数据
     *
     * @param pid /
     * @return /
     */
    List<Menus> getMenus(Long pid);

    /**
     * 根据ID获取同级与上级数据
     *
     * @param menuDto /
     * @param objects /
     * @return /
     */
    List<MenuDTO> getSuperior(MenuDTO menuDto, List<Menus> objects);

    List<MenuDTO> findByUser(Long currentUserId);
}
