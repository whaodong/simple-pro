package pers.hd.simplepro.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.hd.simplepro.core.exception.BadRequestException;
import pers.hd.simplepro.core.exception.EntityExistException;
import pers.hd.simplepro.server.jpa.base.JpaQueryDsServiceImpl;
import pers.hd.simplepro.server.dao.MenuDao;
import pers.hd.simplepro.server.model.dto.MenuDTO;
import pers.hd.simplepro.server.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.query.MenuQueryCriteria;
import pers.hd.simplepro.server.model.vo.MenuMetaVO;
import pers.hd.simplepro.server.model.vo.MenuVO;
import pers.hd.simplepro.server.service.MenusService;
import pers.hd.simplepro.server.service.RolesService;
import pers.hd.simplepro.server.util.QueryHelp;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WangHaoDong
 */
@Service
@RequiredArgsConstructor
public class MenusServiceImpl extends JpaQueryDsServiceImpl<Menus, Long, MenuDao>
        implements MenusService {

    private final RolesService roleService;

    @Override
    public Page<Menus> queryAll(MenuQueryCriteria criteria, Boolean isQuery, Pageable pageable) throws Exception {
        Sort sort = Sort.by(Sort.Direction.ASC, "menuSort");
        if (isQuery) {
            criteria.setPidIsNull(true);
            List<Field> fields = QueryHelp.getAllFields(criteria.getClass(), new ArrayList<>());
            for (Field field : fields) {
                //设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                Object val = field.get(criteria);
                if ("pidIsNull".equals(field.getName())) {
                    continue;
                }
                if (ObjectUtil.isNotNull(val)) {
                    criteria.setPidIsNull(null);
                    break;
                }
            }
        }
        return this.baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
    }

    @Override
    public MenuDTO findById(long id) {
        return new MenuDTO().convertFrom(this.find(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Menus resources) {
        if (this.baseRepository.existsByTitle(resources.getTitle())) {
            throw new EntityExistException(Menus.class, "title", resources.getTitle());
        }
        if (StringUtils.isNotBlank(resources.getComponentName())) {
            if (this.baseRepository.existsByComponentName(resources.getComponentName())) {
                throw new EntityExistException(Menus.class, "componentName", resources.getComponentName());
            }
        }
        if (resources.getPid().equals(0L)) {
            resources.setPid(null);
        }
        if (resources.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(resources.getPath().toLowerCase().startsWith(http) || resources.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        this.save(resources);
        // 计算子节点数目
        resources.setSubCount(0);
        // 更新父节点菜单数目
        updateSubCnt(resources.getPid());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Menus resources) {
        if (resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        if (resources.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(resources.getPath().toLowerCase().startsWith(http) || resources.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
        Menus menu = this.find(resources.getId());
        Menus menu1 = this.baseRepository.findByTitle(resources.getTitle());

        if (menu1 != null && !menu1.getId().equals(menu.getId())) {
            throw new EntityExistException(Menus.class, "title", resources.getTitle());
        }

        if (resources.getPid().equals(0L)) {
            resources.setPid(null);
        }

        // 记录的父节点ID
        Long oldPid = menu.getPid();
        Long newPid = resources.getPid();

        if (StringUtils.isNotBlank(resources.getComponentName())) {
            menu1 = this.baseRepository.findByComponentName(resources.getComponentName());
            if (menu1 != null && !menu1.getId().equals(menu.getId())) {
                throw new EntityExistException(Menus.class, "componentName", resources.getComponentName());
            }
        }
        menu.setTitle(resources.getTitle());
        menu.setComponent(resources.getComponent());
        menu.setPath(resources.getPath());
        menu.setIcon(resources.getIcon());
        menu.setIFrame(resources.getIFrame());
        menu.setPid(resources.getPid());
        menu.setMenuSort(resources.getMenuSort());
        menu.setCache(resources.getCache());
        menu.setHidden(resources.getHidden());
        menu.setComponentName(resources.getComponentName());
        menu.setPermission(resources.getPermission());
        menu.setType(resources.getType());
        this.save(menu);
        // 计算父级菜单节点数目
        updateSubCnt(oldPid);
        updateSubCnt(newPid);
    }

    @Override
    public Set<Menus> getChildMenus(List<Menus> menuList, Set<Menus> menuSet) {
        for (Menus menu : menuList) {
            menuSet.add(menu);
            List<Menus> menus = this.baseRepository.findByPid(menu.getId());
            if (menus != null && menus.size() != 0) {
                getChildMenus(menus, menuSet);
            }
        }
        return menuSet;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Menus> menuSet) {
        for (Menus menu : menuSet) {
            this.delete(menu.getId());
            updateSubCnt(menu.getPid());
        }
    }

    @Override
    public List<Menus> getMenus(Long pid) {
        List<Menus> menus;
        if (pid != null && !pid.equals(0L)) {
            menus = this.baseRepository.findByPid(pid);
        } else {
            menus = this.baseRepository.findByPidIsNull();
        }
        return menus;
    }

    @Override
    public List<MenuDTO> getSuperior(MenuDTO menuDto, List<Menus> menus) {
        if (menuDto.getPid() == null) {
            menus.addAll(this.baseRepository.findByPidIsNull());
            return menus.stream().map(menu -> (MenuDTO) new MenuDTO().convertFrom(menu)).collect(Collectors.toList());
        }
        menus.addAll(this.baseRepository.findByPid(menuDto.getPid()));
        return getSuperior(findById(menuDto.getPid()), menus);
    }

    @Override
    public List<MenuDTO> findByUser(Long currentUserId) {
        Set<RoleSmallDTO> roles = roleService.findByUserId(currentUserId);
        Set<Long> roleIds = roles.stream().map(RoleSmallDTO::getId).collect(Collectors.toSet());
        LinkedHashSet<Menus> menus = this.baseRepository.findByRoleIdsAndTypeNot(roleIds, 2);
        return menus.stream().map(menu -> (MenuDTO) new MenuDTO().convertFrom(menu)).collect(Collectors.toList());
    }

    @Override
    public List<MenuDTO> buildTree(List<MenuDTO> menuDtos) {
        List<MenuDTO> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDTO menuDTO : menuDtos) {
            if (menuDTO.getPid() == null) {
                trees.add(menuDTO);
            }
            for (MenuDTO it : menuDtos) {
                if (menuDTO.getId().equals(it.getPid())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<>());
                    }
                    menuDTO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        if (trees.size() == 0) {
            trees = menuDtos.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    @Override
    public List<MenuVO> buildMenus(List<MenuDTO> menuDtos) {
        List<MenuVO> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDTO> menuDtoList = menuDTO.getChildren();
                        MenuVO menuVo = new MenuVO();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getTitle());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == null ? "/" + menuDTO.getPath() : menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if (!menuDTO.getIFrame()) {
                            if (menuDTO.getPid() == null) {
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                                // 如果不是一级菜单，并且菜单类型为目录，则代表是多级菜单
                            } else if (menuDTO.getType() == 0) {
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent()) ? "ParentView" : menuDTO.getComponent());
                            } else if (StringUtils.isNoneBlank(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVO(menuDTO.getTitle(), menuDTO.getIcon(), !menuDTO.getCache()));
                        if (CollectionUtil.isNotEmpty(menuDtoList)) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid() == null) {
                            MenuVO menuVo1 = new MenuVO();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getIFrame()) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVO> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    private void updateSubCnt(Long menuId) {
        if (menuId != null) {
            int count = this.baseRepository.countByPid(menuId);
            this.baseRepository.updateSubCntById(count, menuId);
        }
    }
}