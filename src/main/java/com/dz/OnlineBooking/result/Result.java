package com.dz.OnlineBooking.result;

import lombok.Data;

@Data
public class Result {

    String code;
    String msg;
    Object data;

    public Result() {
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
