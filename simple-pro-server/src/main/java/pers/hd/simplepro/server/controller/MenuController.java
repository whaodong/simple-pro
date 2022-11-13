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
import pers.hd.simplepro.server.model.dto.MenuDTO;
import pers.hd.simplepro.server.model.entity.Menus;
import pers.hd.simplepro.server.model.params.MenusParam;
import pers.hd.simplepro.server.model.query.MenuQueryCriteria;
import pers.hd.simplepro.server.model.support.ResponseResult;
import pers.hd.simplepro.server.service.MenusService;
import pers.hd.simplepro.server.util.SecurityUtils;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private final MenusService menuService;
    private static final String ENTITY_NAME = "menu";

    @GetMapping(value = "/build")
    public ResponseEntity<?> buildMenus() throws Exception {
        List<MenuDTO> menuDtoList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        List<MenuDTO> menuDtos = menuService.buildTree(menuDtoList);
        return ResponseResult.success(menuService.buildMenus(menuDtos));
    }

    @GetMapping(value = "/lazy")
    public ResponseEntity<?> query(@RequestParam Long pid) {
        return ResponseResult.success(menuService.getMenus(pid));
    }

    @GetMapping(value = "/child")
    public ResponseEntity<?> child(@RequestParam Long id) {
        Set<Menus> menuSet = new HashSet<>();
        List<Menus> menuList = menuService.getMenus(id);
        menuSet.add(menuService.find(id));
        menuSet = menuService.getChildMenus(menuList, menuSet);
        Set<Long> ids = menuSet.stream().map(Menus::getId).collect(Collectors.toSet());
        return ResponseResult.success(ids);
    }

    @GetMapping
    public ResponseEntity<?> query(MenuQueryCriteria criteria,
                                   Pageable pageable) throws Exception {
        Page<Menus> menuDtoList = menuService.queryAll(criteria, true, pageable);
        menuDtoList.map(menus -> new MenuDTO().convertFrom(menus));
        return ResponseResult.success(menuDtoList);
    }

    @PostMapping("/superior")
    public ResponseEntity<?> getSuperior(@RequestBody List<Long> ids) {
        Set<MenuDTO> menuDtos = new LinkedHashSet<>();
        if (CollectionUtil.isNotEmpty(ids)) {
            for (Long id : ids) {
                MenuDTO menuDto = menuService.findById(id);
                menuDtos.addAll(menuService.getSuperior(menuDto, new ArrayList<>()));
            }
            return new ResponseEntity<>(menuService.buildTree(new ArrayList<>(menuDtos)), HttpStatus.OK);
        }
        return ResponseResult.success(menuService.getMenus(null));
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody MenusParam resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        menuService.createMenus(resources);
        return ResponseResult.success(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody MenusParam resources) {
        menuService.updateMenus(resources);
        return ResponseResult.success(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Set<Long> ids) {
        Set<Menus> menuSet = new HashSet<>();
        for (Long id : ids) {
            List<Menus> menuList = menuService.getMenus(id);
            menuSet.add(menuService.find(id));
            menuSet = menuService.getChildMenus(menuList, menuSet);
        }
        menuService.delete(menuSet);
        return ResponseResult.success(HttpStatus.OK);
    }
}
