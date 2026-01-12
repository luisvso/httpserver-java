package br.luis.httpserver.config.exception;

public class HttpException extends RuntimeException {

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable e) {
        super(message, e);
    }

    public HttpException(Throwable e) {
        super(e);
    }
}
