package com.lvmoney.jwt.util;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/4/8
 * Copyright xxxx科技有限公司
 */

import com.lvmoney.common.util.WildcardUtil;

import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 下午4:23:28
 */

public class FilterMapUtil {
    public static boolean wildcardMatchMapKey(Map<String, String> map, String path, String eq) {
        boolean result = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (WildcardUtil.simpleWildcardMatch(key, path) && value.equals(eq)) {
                result = true;
                break;
            }
        }
        return result;
    }
}

