package com.lvmoney.oauth2.center.server.service.impl;/**
 * 描述:
 * 包名:com.revengemission.sso.oauth2.server.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/7/26
 * Copyright XXXXXX科技有限公司
 */


import com.github.dozermapper.core.Mapper;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.persistence.entity.OauthClientEntity;
import com.lvmoney.oauth2.center.server.persistence.repository.OauthClientRepository;
import com.lvmoney.oauth2.center.server.service.Oauth2RedisService;
import com.lvmoney.oauth2.center.server.vo.OauthClient;
import com.lvmoney.oauth2.center.server.ro.Oauth2ClientRo;
import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.service.OauthClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/26 16:35
 */
@Service
public class RedisOauthClientServiceImpl implements OauthClientService {
    @Autowired
    Oauth2RedisService oauth2RedisService;
    @Autowired
    OauthClientRepository oauthClientRepository;
    @Autowired
    Mapper dozerMapper;

    @Override
    public OauthClient findByClientId(String clientId) {
        Oauth2ClientRo oauth2ClientRo = new Oauth2ClientRo();
        Map<String, OauthClient> data = new HashMap<>();
        OauthClient iOauthClient = new OauthClient();
        iOauthClient.setClientId("SampleClientId");
        iOauthClient.setClientSecret("$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96");
        iOauthClient.setScope("user_info");
        iOauthClient.setAuthorizedGrantTypes("authorization_code,refresh_token,password");
        iOauthClient.setWebServerRedirectUri("http://localhost:8030/login");
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        iOauthClient.setAuthorities("ROLE_TRUSTED_CLIENT");
        iOauthClient.setAccessTokenValidity(3600);
        iOauthClient.setRefreshTokenValidity(2592000);
        iOauthClient.setId("1");
        iOauthClient.setApplicationName("SampleClinetId 测试应用");
        iOauthClient.setRecordStatus(0);
        iOauthClient.setVersion(0);
        data.put("SampleClientId", iOauthClient);
        oauth2ClientRo.setData(data);
        oauth2ClientRo.setExpire(180000l);
        oauth2RedisService.client2Redis(oauth2ClientRo);
        OauthClient oauthClient = oauth2RedisService.getClientByClientId(clientId);
        if (oauthClient == null) {
            throw new BusinessException(Oauth2Exception.Proxy.CLIENT_NOT_EXSIT);
        }
        return oauthClient;
    }


    @Override
    public JsonObjects<OauthClient> list(int pageNum, int pageSize, String sortField, String sortOrder) {
        JsonObjects<OauthClient> jsonObjects = new JsonObjects<>();
        Sort sort = null;
        if (StringUtils.equalsIgnoreCase(sortOrder, "asc")) {
            sort = new Sort(Sort.Direction.ASC, sortField);
        } else {
            sort = new Sort(Sort.Direction.DESC, sortField);
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<OauthClientEntity> page = oauthClientRepository.findAll(pageable);
        if (page.getContent() != null && page.getContent().size() > 0) {
            jsonObjects.setRecordsTotal(page.getTotalElements());
            jsonObjects.setRecordsFiltered(page.getTotalElements());
            page.getContent().forEach(u -> {
                jsonObjects.getData().add(dozerMapper.map(u, OauthClient.class));
            });
        }
        return jsonObjects;
    }

    @Override
    @Transactional
    public OauthClient create(OauthClient oauthClient) {
        OauthClientEntity exist = oauthClientRepository.findByClientId(oauthClient.getClientId());
        if (exist != null) {
            throw new BusinessException(Oauth2Exception.Proxy.CLIENT_NOT_EXSIT);
        }
        OauthClientEntity oauthClientEntity = dozerMapper.map(oauthClient, OauthClientEntity.class);
        oauthClientRepository.save(oauthClientEntity);
        return dozerMapper.map(oauthClientEntity, OauthClient.class);
    }

    @Override
    public OauthClient retrieveById(long id) {
        Optional<OauthClientEntity> entityOptional = oauthClientRepository.findById(id);
        return dozerMapper.map(entityOptional.get(), OauthClient.class);
    }

    @Override
    @Transactional
    public OauthClient updateById(OauthClient oauthClient) {
        Optional<OauthClientEntity> entityOptional = oauthClientRepository.findById(Long.parseLong(oauthClient.getId()));
        OauthClientEntity e = entityOptional.get();
        if (StringUtils.isNotEmpty(oauthClient.getClientSecret())) {
            e.setClientSecret(oauthClient.getClientSecret());
        }
        e.setAuthorities(oauthClient.getAuthorities());
        e.setScope(oauthClient.getScope());
        e.setAuthorizedGrantTypes(oauthClient.getAuthorizedGrantTypes());
        e.setWebServerRedirectUri(oauthClient.getWebServerRedirectUri());

        if (StringUtils.isNotEmpty(oauthClient.getRemarks())) {
            e.setRemarks(oauthClient.getRemarks());
        }

        oauthClientRepository.save(e);
        return dozerMapper.map(e, OauthClient.class);
    }

    @Override
    @Transactional
    public void updateRecordStatus(long id, int recordStatus) {
        Optional<OauthClientEntity> entityOptional = oauthClientRepository.findById(id);
        OauthClientEntity e = entityOptional.get();
        e.setRecordStatus(recordStatus);
        oauthClientRepository.save(e);
    }

}
