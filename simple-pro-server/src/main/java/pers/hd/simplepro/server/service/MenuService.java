package pers.hd.simplepro.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.hd.simplepro.core.jpa.base.JpaQueryDsService;
import pers.hd.simplepro.server.dao.MenuDao;
import pers.hd.simplepro.server.pojo.dto.MenuDto;
import pers.hd.simplepro.server.pojo.entity.Menu;
import pers.hd.simplepro.server.pojo.query.MenuQueryCriteria;

import java.util.List;
import java.util.Set;

/**
 * @author WangHaoDong
 */
public interface MenuService extends JpaQueryDsService<Menu, Long, MenuDao> {
    /**
     * 查询全部数据
     *
     * @param criteria 条件
     * @param isQuery  /
     * @return /
     * @throws Exception /
     */
    Page<Menu> queryAll(MenuQueryCriteria criteria, Boolean isQuery, Pageable pageable) throws Exception;

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    MenuDto findById(long id);

    /**
     * 创建
     *
     * @param resources /
     */
    void create(Menu resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Menu resources);

    /**
     * 获取所有子节点，包含自身ID
     *
     * @param menuList /
     * @param menuSet  /
     * @return /
     */
    Set<Menu> getChildMenus(List<Menu> menuList, Set<Menu> menuSet);

    /**
     * 构建菜单树
     *
     * @param menuDtos 原始数据
     * @return /
     */
    List<MenuDto> buildTree(List<MenuDto> menuDtos);

    /**
     * 构建菜单树
     *
     * @param menuDtos /
     * @return /
     */
    Object buildMenus(List<MenuDto> menuDtos);

    /**
     * 删除
     *
     * @param menuSet /
     */
    void delete(Set<Menu> menuSet);

    /**
     * 懒加载菜单数据
     *
     * @param pid /
     * @return /
     */
    List<Menu> getMenus(Long pid);

    /**
     * 根据ID获取同级与上级数据
     *
     * @param menuDto /
     * @param objects /
     * @return /
     */
    List<MenuDto> getSuperior(MenuDto menuDto, List<Menu> objects);

    List<MenuDto> findByUser(Long currentUserId);
}
