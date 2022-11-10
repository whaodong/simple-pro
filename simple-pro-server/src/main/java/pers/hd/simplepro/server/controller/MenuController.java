package pers.hd.simplepro.server.controller;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.hd.simplepro.core.exception.BadRequestException;
import pers.hd.simplepro.server.pojo.dto.MenuDto;
import pers.hd.simplepro.server.pojo.entity.Menu;
import pers.hd.simplepro.server.pojo.query.MenuQueryCriteria;
import pers.hd.simplepro.server.pojo.support.ResponseResult;
import pers.hd.simplepro.server.service.MenuService;
import pers.hd.simplepro.server.util.SecurityUtils;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;
    private static final String ENTITY_NAME = "menu";

    @GetMapping(value = "/build")
    public ResponseEntity<?> buildMenus() throws Exception {
        List<MenuDto> menuDtoList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        List<MenuDto> menuDtos = menuService.buildTree(menuDtoList);
        return ResponseResult.success(menuService.buildMenus(menuDtos));
    }

    @GetMapping(value = "/lazy")
    public ResponseEntity<?> query(@RequestParam Long pid) {
        return ResponseResult.success(menuService.getMenus(pid));
    }

    @GetMapping(value = "/child")
    public ResponseEntity<?> child(@RequestParam Long id) {
        Set<Menu> menuSet = new HashSet<>();
        List<Menu> menuList = menuService.getMenus(id);
        menuSet.add(menuService.find(id));
        menuSet = menuService.getChildMenus(menuList, menuSet);
        Set<Long> ids = menuSet.stream().map(Menu::getId).collect(Collectors.toSet());
        return ResponseResult.success(ids);
    }

    @GetMapping
    public ResponseEntity<?> query(MenuQueryCriteria criteria, Pageable pageable) throws Exception {
        Page<Menu> menuDtoList = menuService.queryAll(criteria, true, pageable);
        return ResponseResult.success(menuDtoList);
    }

    @PostMapping("/superior")
    public ResponseEntity<?> getSuperior(@RequestBody List<Long> ids) {
        Set<MenuDto> menuDtos = new LinkedHashSet<>();
        if (CollectionUtil.isNotEmpty(ids)) {
            for (Long id : ids) {
                MenuDto menuDto = menuService.findById(id);
                menuDtos.addAll(menuService.getSuperior(menuDto, new ArrayList<>()));
            }
            return new ResponseEntity<>(menuService.buildTree(new ArrayList<>(menuDtos)), HttpStatus.OK);
        }
        return ResponseResult.success(menuService.getMenus(null));
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody Menu resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        menuService.create(resources);
        return ResponseResult.success(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Menu resources) {
        menuService.update(resources);
        return ResponseResult.success(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Set<Long> ids) {
        Set<Menu> menuSet = new HashSet<>();
        for (Long id : ids) {
            List<Menu> menuList = menuService.getMenus(id);
            menuSet.add(menuService.find(id));
            menuSet = menuService.getChildMenus(menuList, menuSet);
        }
        menuService.delete(menuSet);
        return ResponseResult.success(HttpStatus.OK);
    }
}
