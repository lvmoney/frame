package com.lvmoney.oauth2.center.server.service.impl;

import com.github.dozermapper.core.Mapper;
import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.db.dao.ScopeDefinitionDao;
import com.lvmoney.oauth2.center.server.db.entity.ScopeDefinition;
import com.lvmoney.oauth2.center.server.service.ScopeDefinitionService;
import com.lvmoney.oauth2.center.server.vo.ScopeDefinitionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class ScopeDefinitionServiceImpl implements ScopeDefinitionService {

    @Autowired
    ScopeDefinitionDao scopeDefinitionDao;

    @Autowired
    Mapper dozerMapper;

    @Override
    public ScopeDefinitionVo findByScope(String scope) {
        ScopeDefinition scopeDefinition = scopeDefinitionDao.findByScope(scope);
        if (scopeDefinition != null) {
            return dozerMapper.map(scopeDefinition, ScopeDefinitionVo.class);
        } else {
            throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_SCOPE_DEFINITION_ERROR);
        }
    }

}
