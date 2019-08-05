package com.lvmoney.captcha.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/17
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.captcha.ro.ValidateCodeRo;
import com.lvmoney.captcha.vo.ValidateResultVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface CaptchaService {
    /**
     * @describe: 保存验证码数据到redis中
     * @param: [validateCodeRo]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/2/15 22:22
     */
    void saveValidaCode2Redis(ValidateCodeRo validateCodeRo);

    ValidateResultVo encodeBase64ImgCode(boolean is2Redis, boolean isDrawLine, int validCodeSize, int fc, int bc, String fontType);

    ValidateResultVo encodeBase64ImgCode();

    /**
     * @describe: 验证码：com.revengemission.commons
     * @param: []
     * @return: com.lvmoney.captcha.vo.ValidateResultVo
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/28 16:55
     */
    ValidateResultVo getCaptcha(int width, int height, int length);

    /**
     * @describe: 通过编号获得验证码
     * @param: [serialNumber]
     * @return: com.lvmoney.captcha.ro.ValidateCodeRo
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/28 15:43
     */
    ValidateCodeRo getValidate(String serialNumber);

    /**
     * @describe: 删除验证码
     * @param: [serialNumber]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/28 15:48
     */
    void deleteValidate(String serialNumber);

}
