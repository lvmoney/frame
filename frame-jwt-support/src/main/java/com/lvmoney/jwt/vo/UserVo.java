/**
 * 描述:
 * 包名:com.lvmoney.jwt.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:32:50
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.jwt.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月4日 下午2:32:50
 */
@Data
public class UserVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 8327049017887409671L;
    String userId;
    String username;
    String password;

}
