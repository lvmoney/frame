package com.lvmoney.common.utils;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.utils
 * 版本信息: 版本1.0
 * 日期:2019/9/19
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/19 14:09
 */
public class ModuleUtil {
    private static final String RELACE_TARGET = "target";
    private static final String RELACE_CLASSES = "classes";
    private static final String CLASSPATH = "classpath:";

    /**
     * 获得当前module的项目根路径，以方便获得对应的配置文件
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/19 14:15
     */
    public static String getModuleRootPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL(CLASSPATH).getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String rootPath = path.getPath().replaceAll(RELACE_TARGET, "").replaceAll(RELACE_CLASSES, "");
        return rootPath;
    }

}
