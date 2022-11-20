package pers.hd.simplepro.server.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.hd.simplepro.server.domain.model.support.ResponseResult;
import pers.hd.simplepro.server.security.service.OnlineUserService;
import pers.hd.simplepro.server.util.EncryptUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/online")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    @GetMapping
    public ResponseEntity<?> queryOnlineUser(String filter, Pageable pageable){
        return ResponseResult.success(onlineUserService.getAll(filter, pageable));
    }

    @GetMapping(value = "/download")
    public void exportOnlineUser(HttpServletResponse response, String filter) throws IOException {
        onlineUserService.download(onlineUserService.getAll(filter), response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOnlineUser(@RequestBody Set<String> keys) throws Exception {
        for (String key : keys) {
            // 解密Key
            key = EncryptUtils.desDecrypt(key);
            onlineUserService.kickOut(key);
        }
        return ResponseResult.success(HttpStatus.OK);
    }
}
