package com.mouse.common.exception;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/1
 */
public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 6512995684962626608L;
    private int status;
    private String reason;
    private String content;

    public int getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }

    public String getContent() {
        return this.content;
    }

    public HttpException(int status, String reason) {
        super("status=" + status + ",reason=" + reason);
        this.status = status;
        this.reason = reason;
    }

    public HttpException(int status, String reason, Throwable th) {
        super("status=" + status + ",reason=" + reason, th);
        this.status = status;
        this.reason = reason;
    }

    public HttpException(int status, String reason, String content) {
        super("status=" + status + ",reason=" + reason + ",content=" + content);
        this.status = status;
        this.reason = reason;
        this.content = content;
    }

}
