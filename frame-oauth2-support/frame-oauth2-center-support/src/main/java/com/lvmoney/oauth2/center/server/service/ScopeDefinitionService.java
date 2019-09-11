package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.ScopeDefinitionVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface ScopeDefinitionService {
    /**
     * 获得路由规则
     *
     * @param scope: 范围
     * @throws
     * @return: com.lvmoney.oauth2.center.server.vo.ScopeDefinitionVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:02
     */
    ScopeDefinitionVo findByScope(String scope);
}
