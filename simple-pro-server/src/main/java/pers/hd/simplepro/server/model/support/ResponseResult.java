package pers.hd.simplepro.server.model.support;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import pers.hd.simplepro.core.util.HttpStatus;

import static org.springframework.http.HttpStatus.*;

/**
 * @author WangHaoDong
 */
public class ResponseResult {

    public static <T> ResponseEntity<Result<T>> fail() {
        return fail("操作失败!");
    }

    public static <T> ResponseEntity<Result<T>> fail(String message) {
        return fail(HttpStatus.BAD_REQUEST.value(), message);
    }

    public static <T> ResponseEntity<Result<T>> fail(HttpStatus httpCode) {
        return fail(httpCode.value(), "");
    }

    public static <T> ResponseEntity<Result<T>> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setTimeStamp();
        result.setMessage(message);
        result.setStatus(code);
        return new ResponseEntity<>(result, getHeader(), OK);
    }

    public static <T> ResponseEntity<Result<T>> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setTimeStamp();
        result.setMessage(message);
        result.setStatus(code);
        return new ResponseEntity<>(result, getHeader(), INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<Result<T>> unAuthorized(String message) {
        Result<T> result = new Result<>();
        result.setTimeStamp();
        result.setMessage(message);
        result.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(result, getHeader(), UNAUTHORIZED);
    }

    public static <T> ResponseEntity<Result<T>> wait(String message) {
        Result<T> result = new Result<>();
        result.setTimeStamp();
        result.setMessage(message);
        result.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, getHeader(), OK);
    }

    public static <T> ResponseEntity<Result<T>> success(String message) {
        Result<T> result = new Result<>();
        result.setStatus(HttpStatus.OK);
        result.setTimeStamp();
        result.setMessage(message);
        return success(result);
    }

    public static <T> ResponseEntity<Result<T>> success(T content) {
        Result<T> result = new Result<>();
        result.setStatus(HttpStatus.OK);
        result.setTimeStamp();
        result.setMessage("操作成功!");
        result.setContent(content);
        return success(result);
    }

    public static <T> ResponseEntity<Result<T>> success(T content, String message) {
        Result<T> result = new Result<>();
        result.setStatus(HttpStatus.OK);
        result.setTimeStamp();
        result.setMessage(message);
        result.setContent(content);
        return success(result);
    }

    public static <T> ResponseEntity<Result<T>> success(Result<T> result) {
        return new ResponseEntity<>(result, getHeader(), OK);
    }

    public static <T> ResponseEntity<PageResult<T>> success(Page<T> page) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setStatus(HttpStatus.OK);
        pageResult.setTimeStamp();
        pageResult.setMessage("操作成功!");
        BeanUtils.copyProperties(page, pageResult);
        /* pageResult.setTotal(page.getTotalPages());//当前为什么没有拷贝total属性？*/
        pageResult.setTotal((int) page.getTotalElements());
        int totalPage = (int) page.getTotalElements() / page.getSize();
        if ((int) page.getTotalElements() % page.getSize() > 0) {
            totalPage = totalPage + 1;
        }
        pageResult.setTotalPage(totalPage);
        return new ResponseEntity<>(pageResult, getHeader(), OK);
    }

    public static <T> ResponseEntity<PageResult<T>> success(PageResult<T> pageResult) {
        return new ResponseEntity<>(pageResult, getHeader(), OK);
    }

    private static HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }
}
