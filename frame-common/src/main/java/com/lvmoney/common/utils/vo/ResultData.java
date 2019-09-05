package com.lvmoney.common.utils.vo;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.ExceptionType;
import com.lvmoney.common.utils.JsonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultData<T> implements Serializable {
    private static final int CODE_SUCCESS = 2000;
    private static final long serialVersionUID = 1L;

    private Integer code;

    private boolean success;

    private String msg;

    private T data;

    private Long date;

    public ResultData() {
        this.setCode(CODE_SUCCESS
        );
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public ResultData(T data) {
        this(CODE_SUCCESS, null, data);
    }

    public ResultData(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResultData(String key, T value) {
        this.date = System.currentTimeMillis();
        this.code = 0;
        Map<String, T> m = new HashMap<String, T>(1);
        m.put(key, value);
        data = (T) m;
    }

    public ResultData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        date = System.currentTimeMillis();
    }

    public ResultData(BusinessException ex) {
        ExceptionType type = ex.getType();
        String exmsg = ex.getDescription();
        String typeExmsg = type.getDescription();
        this.code = type.getCode();
        if (exmsg == null || exmsg.equals("")) {
            this.msg = typeExmsg;
        } else {
            this.msg = exmsg;
        }
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public static void main(String[] args) {
        ResultData resultData = new ResultData();
        resultData.setSuccess(true);
        System.out.println(JsonUtil.t2JsonString(resultData));
    }
}
