/**
 * 描述:
 * 包名:com.lvmoney.mongo.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午9:23:02
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.service.impl;

import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.mongo.service.BaseMongoService;
import com.lvmoney.mongo.vo.BaseMongoCollective;
import com.lvmoney.mongo.vo.BaseMongoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 上午9:23:02
 */
@Service("frameBaseMongoService")
public class BaseMongoServiceImpl implements BaseMongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String SORT_DESC = "desc";
    private static final String SORT_ASC = "desc";

    @Override
    public void save(BaseMongoVo baseVo) {
        mongoTemplate.save(baseVo.getData());
    }

    /**
     * @param baseVo data mongo的mo collectionName 集合名
     * @param baseVo 2019年1月10日上午10:01:11
     * @describe:指定集合保存数据对象
     * @author: lvmoney /xxxx科技有限公司
     */
    public void saveByCollectionName(BaseMongoVo baseVo) {
        mongoTemplate.save(baseVo.getData(), baseVo.getCollectionName());
    }


    @Override
    public void remove(BaseMongoVo baseVo) {

        mongoTemplate.remove(baseVo.getData());
    }


    @Override
    public void removeByCollectionName(BaseMongoVo baseVo) {

        mongoTemplate.remove(baseVo.getData(), baseVo.getCollectionName());
    }


    @Override
    public void removeById(BaseMongoVo baseVo) {
        Criteria criteria = Criteria.where(baseVo.getKey()).is(baseVo.getData());
        criteria.and(baseVo.getKey()).is(baseVo.getData());
        Query query = Query.query(criteria);
        mongoTemplate.remove(query, baseVo.getCollectionName());
    }


    @Override
    public void updateFirst(BaseMongoCollective baseCollection) {
        // 修改条件 key
        String accordingKey = baseCollection.getKey();
        // 修改内容 key数组
        String[] updateKeys = baseCollection.getKeys();
        // 修改条件 value
        Object accordingValue = baseCollection.getData();
        // 修改内容 value数组
        Object[] updateValues = baseCollection.getDatas();
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongoTemplate.updateFirst(query, update, baseCollection.getCollectionName());
    }


    @Override
    public void updateMulti(BaseMongoCollective baseCollection) {
        String accordingKey = baseCollection.getKey();
        Object accordingValue = baseCollection.getData();
        String[] updateKeys = baseCollection.getKeys();
        Object[] updateValues = baseCollection.getDatas();
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongoTemplate.updateMulti(query, update, baseCollection.getCollectionName());
    }


    @Override
    public List<?> find(BaseMongoCollective baseCollection) {
        Object baseVo = baseCollection.getData();
        String[] findKeys = baseCollection.getKeys();
        Object[] findValues = baseCollection.getDatas();
        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<?> resultList = mongoTemplate.find(query, baseVo.getClass());
        return resultList;
    }


    @Override
    public List<?> findByCollectionName(BaseMongoCollective baseCollection) {
        Object baseVo = baseCollection.getData();
        String[] findKeys = baseCollection.getKeys();
        Object[] findValues = baseCollection.getDatas();
        String collectionName = baseCollection.getCollectionName();
        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<?> resultList = mongoTemplate.find(query, baseVo.getClass(), collectionName);
        return resultList;
    }


    @Override
    public List<?> sortFindByCollectionName(BaseMongoCollective baseCollection) {
        String sortType = baseCollection.getSortType().toLowerCase();
        if (StringUtils.isBlank(sortType)) {
            throw new BusinessException(CommonException.Proxy.MONGO_SORT_TYPE_IS_REQUIRED);
        }
        if (!SORT_DESC.equals(sortType) || !SORT_ASC.equals(sortType)) {
            throw new BusinessException(CommonException.Proxy.MONGO_SORT_TYPE_IS_ERROR);
        }
        Object baseVo = baseCollection.getData();
        String[] findKeys = baseCollection.getKeys();
        Object[] findValues = baseCollection.getDatas();
        String collectionName = baseCollection.getCollectionName();
        String sort = baseCollection.getSort();
        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        if (SORT_DESC.equals(sortType)) {
            query.with(new Sort(Direction.DESC, sort));
        }
        if (SORT_ASC.equals(sortType)) {
            query.with(new Sort(Direction.ASC, sort));
        }
        List<?> resultList = mongoTemplate.find(query, baseVo.getClass(), collectionName);
        return resultList;
    }


    @Override
    public Object findOne(BaseMongoCollective baseCollection) {
        String[] findKeys = baseCollection.getKeys();
        Object[] findValues = baseCollection.getDatas();
        String collectionName = baseCollection.getCollectionName();
        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, baseCollection.getData().getClass());
    }


    @Override
    public Object findFirstOne(BaseMongoCollective baseCollection) {
        Object baseVo = baseCollection.getData();
        String[] findKeys = baseCollection.getKeys();
        Object[] findValues = baseCollection.getDatas();
        String collectionName = baseCollection.getCollectionName();
        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, baseVo.getClass(), collectionName);
    }


    @Override
    public List<?> findAll(BaseMongoVo baseVo) {
        List<?> resultList = mongoTemplate.findAll(baseVo.getData().getClass());
        return resultList;
    }


    @Override
    public List<?> findAllByCollectionName(BaseMongoVo baseVo) {
        List<?> resultList = mongoTemplate.findAll(baseVo.getData().getClass(), baseVo.getCollectionName());
        return resultList;
    }
}
