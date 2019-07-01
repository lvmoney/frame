package com.lvmoney.redis.vo;

import java.io.Serializable;

/**
 * @param <T>
 * @describe：redis分页实体Vo
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 上午10:07:42
 */
public class PageVo<T> implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 9031576440262968787L;
    private int pageNo = 1;
    private int pageSize = 20;
    private Long total;
    private T data;
    private String key;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
