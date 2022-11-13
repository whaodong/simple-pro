package pers.hd.simplepro.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.hd.simplepro.core.exception.BadRequestException;
import pers.hd.simplepro.server.model.entity.Dicts;
import pers.hd.simplepro.server.model.query.DictQueryCriteria;
import pers.hd.simplepro.server.model.support.ResponseResult;
import pers.hd.simplepro.server.service.DictService;
import pers.hd.simplepro.server.util.QueryHelp;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dict")
public class DictController {

    private final DictService dictService;
    private static final String ENTITY_NAME = "dict";

    @GetMapping(value = "/all")
    public ResponseEntity<?> queryAll() {
        return ResponseResult.success(dictService.findAll());
    }

    @GetMapping
    public ResponseEntity<?> query(DictQueryCriteria dict, Pageable pageable) {
        return ResponseResult.success(dictService.findAll((root, query, cb) -> QueryHelp.getPredicate(root, query, cb, dict), pageable));
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody Dicts dict) {
        if (dict.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        dictService.save(dict);
        return ResponseResult.success(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Dicts resources) {
        dictService.update(resources.getId(), resources);
        return ResponseResult.success(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Set<Long> ids) {
        dictService.delete(ids);
        return ResponseResult.success(HttpStatus.OK);
    }
}
