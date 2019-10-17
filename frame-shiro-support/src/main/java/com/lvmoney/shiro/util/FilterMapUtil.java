/**
 * 描述:
 * 包名:com.lvmoney.shiro.util
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午4:23:28
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.shiro.util;

import java.util.Map;

import com.lvmoney.common.util.WildcardUtil;

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
