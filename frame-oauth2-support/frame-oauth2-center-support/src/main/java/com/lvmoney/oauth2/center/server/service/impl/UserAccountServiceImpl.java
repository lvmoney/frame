package com.lvmoney.oauth2.center.server.service.impl;

import com.github.dozermapper.core.Mapper;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.oauth2.center.server.exception.Oauth2Exception;
import com.lvmoney.oauth2.center.server.persistence.entity.RoleEntity;
import com.lvmoney.oauth2.center.server.persistence.entity.UserAccountEntity;
import com.lvmoney.oauth2.center.server.persistence.repository.RoleRepository;
import com.lvmoney.oauth2.center.server.persistence.repository.UserAccountRepository;
import com.lvmoney.oauth2.center.server.service.UserAccountService;
import com.lvmoney.common.utils.DateUtil;
import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.vo.UserAccount;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Mapper dozerMapper;

    @Value("${signin.failure.max:5}")
    private int failureMax;

    @Override
    public JsonObjects<UserAccount> listByUsername(String username, int pageNum, int pageSize, String sortField, String sortOrder) {
        JsonObjects<UserAccount> jsonObjects = new JsonObjects<>();
        Sort sort = null;
        if (StringUtils.equalsIgnoreCase(sortOrder, "asc")) {
            sort = new Sort(Sort.Direction.ASC, sortField);
        } else {
            sort = new Sort(Sort.Direction.DESC, sortField);
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<UserAccountEntity> page = null;
        if (StringUtils.isBlank(username)) {
            page = userAccountRepository.findAll(pageable);
        } else {
            page = userAccountRepository.findByUsernameLike(username + "%", pageable);
        }
        if (page.getContent() != null && page.getContent().size() > 0) {
            jsonObjects.setRecordsTotal(page.getTotalElements());
            jsonObjects.setRecordsFiltered(page.getTotalElements());
            page.getContent().forEach(u -> {
                jsonObjects.getData().add(dozerMapper.map(u, UserAccount.class));
            });
        }
        return jsonObjects;

    }


    @Override
    public UserAccount findByUsername(String username) {
        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(username);
        if (userAccountEntity != null) {
            return dozerMapper.map(userAccountEntity, UserAccount.class);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    @Async
    public void loginSuccess(String username) {
        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(username);
        if (userAccountEntity != null) {
            userAccountEntity.setFailureCount(0);
            userAccountEntity.setFailureTime(null);
            userAccountRepository.save(userAccountEntity);
        } else {
        }
    }

    @Override
    @Transactional
    public void loginFailure(String username) {
        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(username);
        if (userAccountEntity != null) {
            if (userAccountEntity.getFailureTime() == null) {
                userAccountEntity.setFailureCount(1);
            } else {
                if (DateUtil.beforeToday(userAccountEntity.getFailureTime())) {
                    userAccountEntity.setFailureCount(0);
                } else {
                    userAccountEntity.setFailureCount(userAccountEntity.getFailureCount() + 1);
                }
            }
            userAccountEntity.setFailureTime(new Date());
            if (userAccountEntity.getFailureCount() >= failureMax && userAccountEntity.getRecordStatus() >= 0) {
                userAccountEntity.setRecordStatus(-1);
            }
            userAccountRepository.save(userAccountEntity);
        }
    }

    @Override
    @Transactional
    public UserAccount create(UserAccount userAccount) {
        UserAccountEntity exist = userAccountRepository.findByUsername(userAccount.getUsername());
        if (exist != null) {
            throw new BusinessException(Oauth2Exception.Proxy.USER_EXSIT);
        }
        UserAccountEntity userAccountEntity = dozerMapper.map(userAccount, UserAccountEntity.class);
        userAccountEntity.getRoles().clear();
        if (userAccount.getRoles() != null && userAccount.getRoles().size() > 0) {
            userAccount.getRoles().forEach(e -> {
                RoleEntity roleEntity = roleRepository.findByRoleName(e.getRoleName());
                if (roleEntity != null) {
                    userAccountEntity.getRoles().add(roleEntity);
                }
            });
        }
        userAccountRepository.save(userAccountEntity);
        return dozerMapper.map(userAccountEntity, UserAccount.class);
    }


    @Override
    public UserAccount retrieveById(long id) {
        Optional<UserAccountEntity> entityOptional = userAccountRepository.findById(id);
        return dozerMapper.map(entityOptional.get(), UserAccount.class);
    }

    @Override
    @Transactional
    public UserAccount updateById(UserAccount userAccount) {
        Optional<UserAccountEntity> entityOptional = userAccountRepository.findById(Long.parseLong(userAccount.getId()));
        UserAccountEntity e = entityOptional.get();
        if (StringUtils.isNotEmpty(userAccount.getPassword())) {
            e.setPassword(userAccount.getPassword());
        }
        e.setNickName(userAccount.getNickName());
        e.setBirthday(userAccount.getBirthday());
        e.setMobile(userAccount.getMobile());
        e.setProvince(userAccount.getProvince());
        e.setCity(userAccount.getCity());
        e.setAddress(userAccount.getAddress());
        e.setAvatarUrl(userAccount.getAvatarUrl());
        e.setEmail(userAccount.getEmail());

        userAccountRepository.save(e);
        return userAccount;
    }


    @Override
    @Transactional
    public void updateRecordStatus(long id, int recordStatus) {
        Optional<UserAccountEntity> entityOptional = userAccountRepository.findById(id);
        UserAccountEntity e = entityOptional.get();
        e.setRecordStatus(recordStatus);
        userAccountRepository.save(e);
    }

}
