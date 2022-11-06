package pers.hd.simplepro.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hd.simplepro.core.util.BeanUtils;
import pers.hd.simplepro.server.pojo.dto.UserDTO;
import pers.hd.simplepro.server.pojo.entity.User;
import pers.hd.simplepro.server.pojo.support.ResponseResult;
import pers.hd.simplepro.server.service.UserService;

import java.util.List;

/**
 * @author WangHaoDong
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUser() {
        List<User> all = userService.findAll();
        return ResponseResult.success(BeanUtils.transformFromInBatch(all, UserDTO.class));
    }
}
