package com.yunlong.result;


public class Result<T> {
    private int status = 200;
    private String des;
    private T data;

    public Result(){}

    public Result(T data){
        this.data = data;
    }

    public Result(int status,String des){
        this.status = status;
        this.des = des;
    }
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public static <T> Result<T> error(int status,String des){
        return  new Result<T>(status,des);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
