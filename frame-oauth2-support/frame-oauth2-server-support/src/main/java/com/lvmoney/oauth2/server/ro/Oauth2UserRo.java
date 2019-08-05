package com.lvmoney.oauth2.server.ro;

import com.lvmoney.oauth2.server.vo.Oauth2UserVo;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lvmoney on 2019/1/18.
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Oauth2UserRo implements Serializable {
    private static final long serialVersionUID = 4155227841229379688L;
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
    private Map<String, Oauth2UserVo> data;

}
