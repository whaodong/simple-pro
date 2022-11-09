package pers.hd.simplepro.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hd.simplepro.core.util.BeanUtils;
import pers.hd.simplepro.server.annotation.rest.AnonymousGetMapping;
import pers.hd.simplepro.server.pojo.dto.UserDTO;
import pers.hd.simplepro.server.pojo.entity.User;
import pers.hd.simplepro.server.pojo.support.ResponseResult;
import pers.hd.simplepro.server.service.UserService;

import java.util.List;

/**
 * @author WangHaoDong
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @AnonymousGetMapping
    public ResponseEntity<?> getUser() {
        List<User> all = userService.findAll();
        log.info(passwordEncoder.encode("123456"));
        return ResponseResult.success(BeanUtils.transformFromInBatch(all, UserDTO.class));
    }
}
