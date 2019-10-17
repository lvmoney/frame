package com.lvmoney.common.util;

import java.time.Instant;

import com.lvmoney.common.util.vo.ResultData;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
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
