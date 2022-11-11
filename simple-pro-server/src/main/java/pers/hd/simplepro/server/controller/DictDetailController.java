package pers.hd.simplepro.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.hd.simplepro.core.exception.BadRequestException;
import pers.hd.simplepro.server.model.entity.DictDetail;
import pers.hd.simplepro.server.model.query.DictDetailQueryCriteria;
import pers.hd.simplepro.server.model.support.ResponseResult;
import pers.hd.simplepro.server.service.DictDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dictDetail")
public class DictDetailController {

    private final DictDetailService dictDetailService;
    private static final String ENTITY_NAME = "dictDetail";

    @GetMapping
    public ResponseEntity<?> query(DictDetailQueryCriteria criteria,
                                   @PageableDefault(sort = {"dictSort"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseResult.success(dictDetailService.f(criteria, pageable));
    }

    @GetMapping(value = "/map")
    public ResponseEntity<Object> getDictDetailMaps(@RequestParam String dictName) {
        String[] names = dictName.split("[,，]");
        Map<String, List<DictDetail>> dictMap = new HashMap<>(16);
        for (String name : names) {
            dictMap.put(name, dictDetailService.getRepository().findByLabel(name));
        }
        return new ResponseEntity<>(dictMap, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody DictDetail resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        dictDetailService.save(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@Validated @RequestBody DictDetail resources) {
        dictDetailService.update(resources.getId(), resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        dictDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}