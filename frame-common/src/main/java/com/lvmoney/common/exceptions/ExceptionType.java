package com.lvmoney.common.exceptions;

/**
 * @describe：业务异常类型顶层接口,推荐使用枚举来进行实现
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 上午11:18:50
 */
public interface ExceptionType {
    /**
     * @return 2018年12月29日上午11:19:26
     * @describe:异常代码,为方便前端捕捉,推荐分段处理
     * @author: lvmoney /xxxx科技有限公司
     */
    int getCode();

    /**
     * @return 2018年12月29日上午11:19:37
     * @describe:异常描述信息
     * @author: lvmoney /xxxx科技有限公司
     */
    String getDescription();

}
