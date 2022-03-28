package com.arithmatic.calculator.exception;

public class DataNotFoundException extends Exception{

    private String errorMsg ="Record not Found";

    public DataNotFoundException(String errorMsg){
        super(errorMsg);
        this.errorMsg =errorMsg;
    }

    public DataNotFoundException() {
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
