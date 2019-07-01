package com.lvmoney.k8s.feign.vo.resp;

import lombok.Data;

/**
 * Created by Administrator on 2019/5/23.
 */
@Data
public class CommonVo<T> {
    private Integer code;
    private Long date;
    private String msg;
    private boolean success;
    private T data;
}
