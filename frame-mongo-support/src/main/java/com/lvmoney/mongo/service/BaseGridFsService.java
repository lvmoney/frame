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
     * @describe: 保存文件
     * @param: [baseGridFsVo]
     * @return: BaseGridFsOutVo
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    BaseGridFsOutVo save(BaseGridFsVo baseGridFsVo);

    /**
     * @describe: 批量保存
     * @param: [baseGridFsVos]
     * @return: java.util.List<BaseGridFsOutVo>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */

    List<BaseGridFsOutVo> batchSave(List<BaseGridFsVo> baseGridFsVos);

    /**
     * @describe: 获取单个文件
     * @param: [baseGridFsQueryVo]
     * @return: BaseGridFsByteOutVo
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    BaseGridFsByteOutVo getByMongoId(BaseGridFsQueryVo baseGridFsQueryVo);

    /**
     * @describe: 批量获得
     * @param: [baseGridFsQueryVos]
     * @return: java.util.List<BaseGridFsByteOutVo>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    List<BaseGridFsByteOutVo> batchGetByMongoId(List<BaseGridFsQueryVo> baseGridFsQueryVos);

    /**
     * @describe: 删除指定
     * @param: [baseGridFsQueryVo]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    void deleteByMongoId(BaseGridFsQueryVo baseGridFsQueryVo);

}
