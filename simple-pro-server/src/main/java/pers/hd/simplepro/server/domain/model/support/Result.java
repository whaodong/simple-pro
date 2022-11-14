package pers.hd.simplepro.server.domain.model.support;

import pers.hd.simplepro.server.util.HttpStatus;

/**
 * @author WangHaoDong
 */
public class Result<T> {

    /**
     * 时间戳
     */
    public long timestamp;
    /**
     * 返回状态
     */
    public Integer status;
    /**
     * 返回消息
     */
    private String message;
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
}
