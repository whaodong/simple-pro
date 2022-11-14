package pers.hd.simplepro.server.domain.model.support;

import org.springframework.data.domain.Pageable;
import pers.hd.simplepro.server.util.HttpStatus;

/**
 * @author WangHaoDong
 */
public class PageResult<T> {
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 返回状态
     */
    private Integer status;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 分页总数
     */
    private Integer totalPage;
    /**
     * 分页
     */
    private Pageable pageable;
    /**
     * 返回实体
     */
    private T content;

    public void setTimeStamp() {
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setStatus(HttpStatus code) {
        this.status = code.value();
    }

    public void setStatus(Integer code) {
        this.status = code;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T data) {
        this.content = data;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Pageable getPageable() {
        return this.pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }
}
