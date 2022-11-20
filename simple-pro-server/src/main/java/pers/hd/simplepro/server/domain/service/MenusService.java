package pers.hd.simplepro.server.domain.service;

import pers.hd.simplepro.server.domain.model.dto.MenusDTO;
import pers.hd.simplepro.server.domain.model.entity.Menus;
import pers.hd.simplepro.server.domain.model.params.MenusParam;
import pers.hd.simplepro.server.domain.repository.MenusRepository;
import pers.hd.simplepro.server.domain.service.base.BaseCrudService;

import java.util.List;

/**
 * @author WangHaoDong
 */
public interface MenusService extends BaseCrudService<Menus, String, MenusRepository> {

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    MenusDTO findById(String id);

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
    List<MenusDTO> getChildMenus(List<Menus> menuList, List<MenusDTO> menuSet);

    /**
     * 构建菜单树
     *
     * @param menuDtos 原始数据
     * @return /
     */
    List<MenusDTO> buildTree(List<MenusDTO> menuDtos);

    /**
     * 构建菜单树
     *
     * @param menuDtos /
     * @return /
     */
    Object buildMenus(List<MenusDTO> menuDtos);

    /**
     * 删除
     *
     * @param menuSet /
     */
    void delete(List<MenusDTO> menus);

    /**
     * 懒加载菜单数据
     *
     * @param pid /
     * @return /
     */
    List<Menus> getMenus(String pid);

    /**
     * 根据ID获取同级与上级数据
     *
     * @param menuDto /
     * @param objects /
     * @return /
     */
    List<MenusDTO> getSuperior(MenusDTO menuDto, List<Menus> objects);

    List<MenusDTO> findByUser(String currentUserId);
}
