package com.riservi.gestion.app.service.dtos;

public class MessageDao {

    private String message;

    private String code;

    public MessageDao() {
    }

    public MessageDao(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
