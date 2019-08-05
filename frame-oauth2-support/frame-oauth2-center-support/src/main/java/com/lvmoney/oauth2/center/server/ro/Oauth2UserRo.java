package com.lvmoney.oauth2.center.server.ro;

import com.lvmoney.oauth2.center.server.vo.UserInfo;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lvmoney on 2019/1/18.
 */
@Data
@NoArgsConstructor
public class Oauth2UserRo implements Serializable {
    private static final long serialVersionUID = -339395465817487373L;
    /**
     *
     */


    /**
     * 失效时间
     */
    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, UserInfo> data;

}
