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
     * @describe: word文件转pdf
     * @param: [source, target]源文件支持后缀doc，docx，目标文件pdf,html
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/2/27
     */
    boolean officeChange(WSourceVo wSourceVo);

    /**
     * @describe:文件转换
     * @param: [baseChangeFileVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/16
     */
    BaseChangeByteOutVo officeChange(BaseChangeFileVo baseChangeFileVo);

    /**
     * @describe: 根据模板文件生成word
     * @param: [templateVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/2/28
     */
    boolean template2Word(WTemplateVo templateVo);

    BaseWordByteVo template2Word(WTemplateByteVo templateVo);

    /**
     * @describe: 获取模板文件需要填充的字段值
     * @param: [wSourceVo]
     * @return: java.util.List<WTemplateParams>
     * @author： lvmoney /xxxx科技有限公司
     * 2019/2/28
     */
    List<WTemplateParams> getTemplateParams(WSourceVo wSourceVo);

}
