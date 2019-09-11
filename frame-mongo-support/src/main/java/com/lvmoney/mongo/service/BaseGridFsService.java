/**
 * 描述:
 * 包名:com.lvmoney.mongo.service
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午11:54:23
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.mongo.service;

import com.lvmoney.mongo.vo.BaseGridFsByteOutVo;
import com.lvmoney.mongo.vo.BaseGridFsOutVo;
import com.lvmoney.mongo.vo.BaseGridFsQueryVo;
import com.lvmoney.mongo.vo.BaseGridFsVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 上午11:54:23
 */

public interface BaseGridFsService {
    /**
     * 保存文件
     *
     * @param baseGridFsVo: 基础文件存储实体
     * @return: com.lvmoney.mongo.vo.BaseGridFsOutVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:31
     */
    BaseGridFsOutVo save(BaseGridFsVo baseGridFsVo);

    /**
     * 批量保存
     *
     * @param baseGridFsVos: 批量基础文件存储实体
     * @return: java.util.List<com.lvmoney.mongo.vo.BaseGridFsOutVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:32
     */
    List<BaseGridFsOutVo> batchSave(List<BaseGridFsVo> baseGridFsVos);

    /**
     * 获取单个文件
     *
     * @param baseGridFsQueryVo: 获取文件请求实体
     * @return: com.lvmoney.mongo.vo.BaseGridFsByteOutVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:32
     */
    BaseGridFsByteOutVo getByMongoId(BaseGridFsQueryVo baseGridFsQueryVo);

    /**
     * 批量获得文件
     *
     * @param baseGridFsQueryVos: 批量获取文件请求实体
     * @return: java.util.List<com.lvmoney.mongo.vo.BaseGridFsByteOutVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:33
     */
    List<BaseGridFsByteOutVo> batchGetByMongoId(List<BaseGridFsQueryVo> baseGridFsQueryVos);

    /**
     * 删除指定文件
     *
     * @param baseGridFsQueryVo: 基础文件实体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:33
     */
    void deleteByMongoId(BaseGridFsQueryVo baseGridFsQueryVo);

}
