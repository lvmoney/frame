package com.lvmoney.office.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.office.vo.*;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface WordService {
    /**
     * word文件转pdf,源文件支持后缀doc，docx，目标文件pdf,html
     *
     * @param wSourceVo: 请求实体
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:00
     */
    boolean officeChange(WordSourceVo wSourceVo);

    /**
     * 文件转换
     *
     * @param baseChangeFileVo: 转化实体
     * @throws
     * @return: com.lvmoney.office.vo.BaseChangeByteOutVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:01
     */
    BaseChangeByteOutVo officeChange(BaseChangeFileVo baseChangeFileVo);

    /**
     * 根据模板文件生成word
     *
     * @param templateVo: 模板实体
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:01
     */
    boolean template2Word(WordTemplateVo templateVo);

    /**
     * 根据模板文件生成word
     *
     * @param templateVo: 流文件模板实体
     * @throws
     * @return: com.lvmoney.office.vo.BaseWordByteVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:01
     */
    BaseWordByteVo template2Word(WordTemplateByteVo templateVo);

    /**
     * 获取模板文件需要填充的字段值
     *
     * @param wSourceVo: 对象
     * @throws
     * @return: java.util.List<com.lvmoney.office.vo.WordTemplateParams>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 9:02
     */
    List<WordTemplateParams> getTemplateParams(WordSourceVo wSourceVo);

}
