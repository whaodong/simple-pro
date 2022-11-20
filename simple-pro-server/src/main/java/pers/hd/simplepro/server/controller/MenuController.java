package pers.hd.simplepro.server.controller;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.hd.simplepro.server.domain.model.dto.MenusDTO;
import pers.hd.simplepro.server.domain.model.entity.Menus;
import pers.hd.simplepro.server.domain.model.params.MenusParam;
import pers.hd.simplepro.server.domain.model.query.MenuQueryCriteria;
import pers.hd.simplepro.server.domain.model.support.ResponseResult;
import pers.hd.simplepro.server.domain.service.MenusService;
import pers.hd.simplepro.server.exception.BadRequestException;
import pers.hd.simplepro.server.util.SecurityUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuController {

    private final MenusService menuService;
    private static final String ENTITY_NAME = "menu";

    @GetMapping(value = "/build")
    public ResponseEntity<?> buildMenus() {
        List<MenusDTO> menuDtoList;
        if ("admin".equals(SecurityUtils.getCurrentUsername())) {
            menuDtoList = menuService.getRepository().findByType(2).stream().map(item -> (MenusDTO) new MenusDTO().convertFrom(item)).collect(Collectors.toList());
        } else {
            menuDtoList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        }
        List<MenusDTO> menuDtos = menuService.buildTree(menuDtoList);
        return ResponseResult.success(menuService.buildMenus(menuDtos));
    }

    @GetMapping(value = "/lazy")
    public ResponseEntity<?> query(@RequestParam String pid) {
        return ResponseResult.success(menuService.getMenus(pid));
    }

    @GetMapping(value = "/child")
    public ResponseEntity<?> child(@RequestParam String id) {
        List<MenusDTO> menuSet = new ArrayList<>();
        List<Menus> menuList = menuService.getMenus(id);
        menuSet.add(new MenusDTO().convertFrom(menuService.find(id)));
        menuSet = menuService.getChildMenus(menuList, menuSet);
        Set<String> ids = menuSet.stream().map(MenusDTO::getId).collect(Collectors.toSet());
        return ResponseResult.success(ids);
    }

    @GetMapping
    public ResponseEntity<?> query(MenuQueryCriteria criteria,
                                   Pageable pageable) throws Exception {
        List<Menus> menuDtoList = menuService.findAllByQuery(criteria);
        List<MenusDTO> collect = menuDtoList.stream().map(menus -> (MenusDTO)new MenusDTO().convertFrom(menus)).collect(Collectors.toList());
        return ResponseResult.success(menuService.buildTree(collect));
    }

    @PostMapping("/superior")
    public ResponseEntity<?> getSuperior(@RequestBody List<String> ids) {
        Set<MenusDTO> menuDtos = new LinkedHashSet<>();
        if (CollectionUtil.isNotEmpty(ids)) {
            for (String id : ids) {
                MenusDTO menuDto = menuService.findById(id);
                menuDtos.addAll(menuService.getSuperior(menuDto, new ArrayList<>()));
            }
            return ResponseResult.success(menuService.buildTree(new ArrayList<>(menuDtos)));
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
    public ResponseEntity<?> delete(@RequestBody Set<String> ids) {
        List<MenusDTO> menuSet = new ArrayList<>();
        for (String id : ids) {
            List<Menus> menuList = menuService.getMenus(id);
            menuSet.add(new MenusDTO().convertFrom(menuService.find(id)));
            menuSet = menuService.getChildMenus(menuList, menuSet);
        }
        menuService.delete(menuSet);
        return ResponseResult.success(HttpStatus.OK);
    }
}
