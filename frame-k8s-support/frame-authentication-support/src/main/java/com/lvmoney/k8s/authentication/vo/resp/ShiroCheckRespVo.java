package com.lvmoney.k8s.authentication.vo.resp;/**
 * 描述:
 * 包名:com.lvmoney.k8s.login.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 11:18
 */
@Data
public class ShiroCheckRespVo implements Serializable {
    private static final long serialVersionUID = -5429398583984395971L;
    private boolean pass;
}
