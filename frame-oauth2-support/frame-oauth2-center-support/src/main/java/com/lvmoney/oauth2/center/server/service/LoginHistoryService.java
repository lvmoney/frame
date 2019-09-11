package com.lvmoney.oauth2.center.server.service;

import com.lvmoney.oauth2.center.server.vo.JsonObjects;
import com.lvmoney.oauth2.center.server.vo.LoginHistoryVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface LoginHistoryService {
    /**
     * 分页获得历史信息
     *
     * @param username:  用户名
     * @param pageNum:   第几页
     * @param pageSize:  分页大小
     * @param sortField: 排序字段
     * @param sortOrder: 排序类型
     * @throws
     * @return: com.lvmoney.oauth2.center.server.vo.JsonObjects<com.lvmoney.oauth2.center.server.vo.LoginHistoryVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:51
     */
    JsonObjects<LoginHistoryVo> listByUsername(String username, int pageNum,
                                               int pageSize,
                                               String sortField,
                                               String sortOrder);

    /**
     * 创建历史信息
     *
     * @param loginHistoryVo: 历史信息实体
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:52
     */
    void asyncCreate(LoginHistoryVo loginHistoryVo);

}
