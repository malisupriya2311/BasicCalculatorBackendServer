package com.arithmatic.calculator.exception;

public class BadRequestException extends Exception{

    private String errorMsg ="Bad Request";

    public BadRequestException(String errorMsg){
        super(errorMsg);
        this.errorMsg =errorMsg;
    }

    public BadRequestException() {
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
