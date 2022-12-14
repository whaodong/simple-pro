package pers.hd.simplepro.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.hd.simplepro.server.exception.BadRequestException;
import pers.hd.simplepro.server.util.HttpStatus;
import pers.hd.simplepro.server.config.RsaProperties;
import pers.hd.simplepro.server.domain.model.dto.RoleSmallDTO;
import pers.hd.simplepro.server.domain.model.dto.UsersDTO;
import pers.hd.simplepro.server.domain.model.entity.Users;
import pers.hd.simplepro.server.domain.model.params.UserParam;
import pers.hd.simplepro.server.domain.model.query.UserQueryCriteria;
import pers.hd.simplepro.server.domain.model.support.ResponseResult;
import pers.hd.simplepro.server.domain.model.vo.UserPassVo;
import pers.hd.simplepro.server.domain.service.RolesService;
import pers.hd.simplepro.server.domain.service.UsersService;
import pers.hd.simplepro.server.domain.service.assembler.UsersAssembler;
import pers.hd.simplepro.server.util.RsaUtils;
import pers.hd.simplepro.server.util.SecurityUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author WangHaoDong
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UsersService userService;
    private final PasswordEncoder passwordEncoder;
    private final RolesService roleService;
    private final UsersAssembler usersAssembler;

    public UserController(UsersService userService,
                          PasswordEncoder passwordEncoder,
                          RolesService roleService,
                          UsersAssembler usersAssembler) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.usersAssembler = usersAssembler;
    }

    @GetMapping
    public ResponseEntity<?> query(UserQueryCriteria criteria,
                                   Pageable pageable) {
        return ResponseResult.success(usersAssembler.convertToUsersDTO(userService.findAllByQueryAndPage(criteria, pageable)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody UserParam resources) {
        checkLevel(resources);
        // ???????????? 123456
        resources.setPassword(passwordEncoder.encode("123456"));
        userService.save(resources.convertTo());
        return ResponseResult.success(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody UserParam userParam) throws Exception {
        checkLevel(userParam);
        userService.update(userParam.convertTo());
        return ResponseResult.success(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "center")
    public ResponseEntity<?> center(@Validated @RequestBody Users resources) {
        if (!resources.getId().equals(SecurityUtils.getCurrentUserId())) {
            throw new BadRequestException("????????????????????????");
        }
        userService.update(resources);
        return ResponseResult.success(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Set<String> ids) {
        for (String id : ids) {
            Integer currentLevel = Collections.min(roleService.findByUserId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList()));
            Integer optLevel = Collections.min(roleService.findByUserId(id).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList()));
            if (currentLevel > optLevel) {
                throw new BadRequestException("????????????????????????????????????" + userService.find(id).getUserName());
            }
        }
        for (String id : ids) {
            userService.delete(id);
        }
        return ResponseResult.success(HttpStatus.OK);
    }

    @PostMapping(value = "/updatePass")
    public ResponseEntity<?> updatePass(@RequestBody UserPassVo passVo) throws Exception {
        String oldPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, passVo.getOldPass());
        String newPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, passVo.getNewPass());
        UsersDTO user = userService.findByUsername(SecurityUtils.getCurrentUsername());
        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new BadRequestException("??????????????????????????????");
        }
        if (passwordEncoder.matches(newPass, user.getPassword())) {
            throw new BadRequestException("?????????????????????????????????");
        }
        userService.getRepository().updatePass(user.getUserName(), passwordEncoder.encode(newPass), new Date());
        return ResponseResult.success(HttpStatus.OK);
    }
/*
    @PostMapping(value = "/updateEmail/{code}")
    public ResponseEntity<?> updateEmail(@PathVariable String code, @RequestBody User user) throws Exception {
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, user.getPassword());
        UserDTO userDto = userService.findByUsername(SecurityUtils.getCurrentUsername());
        if (!passwordEncoder.matches(password, userDto.getPassword())) {
            throw new BadRequestException("????????????");
        }
        verificationCodeService.validated(CodeEnum.EMAIL_RESET_EMAIL_CODE.getKey() + user.getEmail(), code);
        userService.updateEmail(userDto.getUsername(), user.getEmail());
        return ResponseResult.success(HttpStatus.OK);
    }*/

    /**
     * ???????????????????????????????????????????????????????????????????????????????????????????????????
     */
    private void checkLevel(UserParam userParam) {
        Integer currentLevel = Collections.min(roleService.findByUserId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList()));
        Integer optLevel = roleService.findByRoles(userParam.getRoles());
        if (currentLevel > optLevel) {
            throw new BadRequestException("??????????????????");
        }
    }
}
