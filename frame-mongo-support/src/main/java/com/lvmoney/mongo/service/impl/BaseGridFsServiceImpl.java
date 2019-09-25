/**
 * 描述:
 * 包名:com.lvmoney.mongo.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午11:54:42
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.service.impl;

import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.mongo.service.BaseGridFsService;
import com.lvmoney.mongo.vo.BaseGridFsByteOutVo;
import com.lvmoney.mongo.vo.BaseGridFsOutVo;
import com.lvmoney.mongo.vo.BaseGridFsQueryVo;
import com.lvmoney.mongo.vo.BaseGridFsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 上午11:54:42
 */
@Service("frameBaseGridFsService")
public class BaseGridFsServiceImpl implements BaseGridFsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseGridFsServiceImpl.class);
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFsBucket;

    @Override
    public BaseGridFsOutVo save(BaseGridFsVo baseGridFsVo) {
        long maxSize = baseGridFsVo.getMaxSize();
        MultipartFile file = baseGridFsVo.getFile();
        long fileSize = file.getSize();
        if (maxSize < file.getSize()) {
            throw new BusinessException(CommonException.Proxy.GRIDFS_FILE_SIZE);
        }
        String baseFileName = baseGridFsVo.getFileName();
        String fileName = StringUtils.isBlank(baseFileName) ? file.getOriginalFilename() : baseFileName;
        // 获得文件类型
        String contentType = file.getContentType();
        // 获得文件输入流
        InputStream ins;
        try {
            ins = file.getInputStream();
            // 将文件存储到mongodb中,mongodb 将会返回这个文件的具体信息
            DBObject dbObj = baseGridFsVo.getDbObj();

            ObjectId objectId = gridFsTemplate.store(ins, fileName, contentType, dbObj);
            BaseGridFsOutVo result = new BaseGridFsOutVo();
            result.setFileName(fileName);
            result.setFileType(contentType);
            result.setMongoFileId(objectId.toString());
            result.setSize(fileSize);
            return result;
        } catch (IOException e) {
            LOGGER.error("文件名为:{},文件类型为:{},保存文件报错:{}", fileName, contentType, e.getMessage());
            throw new BusinessException(CommonException.Proxy.GRIDFS_SAVE_ERROR);
        }


    }

    @Override
    public List<BaseGridFsOutVo> batchSave(List<BaseGridFsVo> baseGridFsVos) {
        List<BaseGridFsOutVo> result = new ArrayList<>();
        baseGridFsVos.forEach(baseGridFsService -> {
            BaseGridFsOutVo baseGridFsOutVo = this.save(baseGridFsService);
            result.add(baseGridFsOutVo);
        });

        return result;
    }

    @Override
    public BaseGridFsByteOutVo getByMongoId(BaseGridFsQueryVo baseGridFsQueryVo) {
        String fileId = baseGridFsQueryVo.getMongoFileId();
        Query query = Query.query(Criteria.where("_id").is(fileId));
        GridFSFile gridFsFile = gridFsTemplate.findOne(query);
        if (gridFsFile == null) {
            throw new BusinessException(CommonException.Proxy.GRIDFS_QUERY_FILE_NOT_EXSIT);
        }
        String fileName = gridFsFile.getFilename();
        // 打开下载流对象
        GridFSDownloadStream gridFs = gridFsBucket.openDownloadStream(gridFsFile.getObjectId());
        // 创建gridFsSource，用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFsFile, gridFs);
        try {
            BaseGridFsByteOutVo result = new BaseGridFsByteOutVo();
            result.setFileByte(IOUtils.toByteArray(gridFsResource.getInputStream()));
            result.setFileName(fileName);
            return result;
        } catch (IllegalStateException | IOException e) {
            LOGGER.error("通过_id{}获得文件报错：{}", fileId, e.getMessage());
            throw new BusinessException(CommonException.Proxy.GRIDFS_QUERY_FILE_ERROR);
        }
    }

    @Override
    public List<BaseGridFsByteOutVo> batchGetByMongoId(List<BaseGridFsQueryVo> baseGridFsQueryVos) {
        Criteria criteria = null;
        String key = "_id";
        for (int i = 0; i < baseGridFsQueryVos.size(); i++) {
            String fileId = baseGridFsQueryVos.get(i).getMongoFileId();
            if (i == 0) {
                criteria = Criteria.where(key).is(fileId);
            } else {
                criteria.and(key).is(fileId);
            }
        }
        Query query = Query.query(criteria);
        GridFSFindIterable gridFsFiles = gridFsTemplate.find(query);
        List<BaseGridFsByteOutVo> result = new ArrayList<>();
        gridFsFiles.forEach((Block<? super GridFSFile>) gridFsFile -> {
            String fileId = gridFsFile.getObjectId().toString();
            BaseGridFsByteOutVo baseGridFsByteOutVo = new BaseGridFsByteOutVo();
            GridFSDownloadStream gridFs = gridFsBucket.openDownloadStream(gridFsFile.getObjectId());
            // 创建gridFsSource，用于获取流对象
            GridFsResource gridFsResource = new GridFsResource(gridFsFile, gridFs);
            try {
                String fileName = gridFsFile.getFilename();
                baseGridFsByteOutVo.setFileByte(IOUtils.toByteArray(gridFsResource.getInputStream()));
                baseGridFsByteOutVo.setFileName(fileName);
                result.add(baseGridFsByteOutVo);
            } catch (IllegalStateException | IOException e) {
                LOGGER.error("通过_id{}获得文件报错：{}", fileId, e.getMessage());
                throw new BusinessException(CommonException.Proxy.GRIDFS_QUERY_FILE_ERROR);
            }
        });
        return result;
    }

    @Override
    public void deleteByMongoId(BaseGridFsQueryVo baseGridFsQueryVo) {
        gridFsTemplate.delete(new Query().addCriteria(Criteria.where("_id").is(baseGridFsQueryVo.getMongoFileId())));
    }
}
