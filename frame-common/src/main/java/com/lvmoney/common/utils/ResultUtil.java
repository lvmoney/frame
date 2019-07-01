package com.lvmoney.common.utils;

import java.time.Instant;

import com.lvmoney.common.utils.vo.ResultData;

public class ResultUtil {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ResultData success(Object object) {
        ResultData result = new ResultData();
        result.setSuccess(true);
        result.setMsg("success");
        result.setData(object);
        result.setDate(Instant.now().toEpochMilli());
        return result;
    }


    @SuppressWarnings({"rawtypes"})
    public static ResultData success() {
        return success(null);
    }


    @SuppressWarnings("rawtypes")
    public static ResultData error(Integer code, String msg) {
        ResultData result = new ResultData();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        result.setDate(Instant.now().toEpochMilli());
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ResultData error(Integer code, String msg, Object obj) {
        ResultData result = new ResultData();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        result.setData(obj);
        result.setDate(Instant.now().toEpochMilli());
        return result;
    }

}
