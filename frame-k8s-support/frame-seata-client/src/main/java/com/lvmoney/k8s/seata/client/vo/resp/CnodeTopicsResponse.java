package com.lvmoney.k8s.seata.client.vo.resp;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */
public class CnodeTopicsResponse {
    private boolean success;
    private List<String> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
