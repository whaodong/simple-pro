package pers.hd.simplepro.server.controller;

import cn.hutool.core.lang.Dict;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.hd.simplepro.server.exception.BadRequestException;
import pers.hd.simplepro.server.domain.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.domain.model.entity.Roles;
import pers.hd.simplepro.server.domain.model.params.RolesParam;
import pers.hd.simplepro.server.domain.model.query.RoleQueryCriteria;
import pers.hd.simplepro.server.domain.model.support.ResponseResult;
import pers.hd.simplepro.server.domain.service.RolesService;
import pers.hd.simplepro.server.util.SecurityUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RolesService roleService;

    private static final String ENTITY_NAME = "role";

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> query(@PathVariable String id) {
        return ResponseResult.success(roleService.find(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> query() {
        return ResponseResult.success(roleService.findAll());
    }

    @GetMapping
    public ResponseEntity<?> query(RoleQueryCriteria criteria, Pageable pageable) {
        return ResponseResult.success(roleService.findAllByQueryAndPage(criteria, pageable));
    }

    @GetMapping(value = "/level")
    public ResponseEntity<?> getLevel() {
        return ResponseResult.success(Dict.create().set("level", getLevels(null)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody Roles resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        getLevels(resources.getLevel());
        roleService.save(resources);
        return ResponseResult.success(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Roles resources) {
        getLevels(resources.getLevel());
        roleService.update(resources.getId(), resources);
        return ResponseResult.success(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/menu")
    public ResponseEntity<?> updateMenu(@RequestBody RolesParam roleParam) {
        Roles role = roleService.find(roleParam.getId());
        getLevels(role.getLevel());
        roleService.updateMenu(roleParam);
        return ResponseResult.success(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Set<String> ids) {
        for (String id : ids) {
            Roles role = roleService.find(id);
            getLevels(role.getLevel());
        }
        // 验证是否被用户关联
        roleService.verification(ids);
        roleService.deleteByIdInBatch(ids);
        return ResponseResult.success(HttpStatus.OK);
    }

    /**
     * 获取用户的角色级别
     *
     * @return /
     */
    private int getLevels(Integer level) {
        List<Integer> levels = roleService.findByUserId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList());
        int min = Collections.min(levels);
        if (level != null) {
            if (level < min) {
                throw new BadRequestException("权限不足，你的角色级别：" + min + "，低于操作的角色级别：" + level);
            }
        }
        return min;
    }
}
