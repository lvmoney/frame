package com.lvmoney.common.utils.dynamic;

import java.io.IOException;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

/**
 * @describe：类文件管理器
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class ClassFileManager extends
        ForwardingJavaFileManager {
    public JavaClassObject getJavaClassObject() {
        return jclassObject;
    }

    private JavaClassObject jclassObject;


    public ClassFileManager(StandardJavaFileManager
                                    standardManager) {
        super(standardManager);
    }


    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
                                               String className, JavaFileObject.Kind kind, FileObject sibling)
            throws IOException {
        jclassObject = new JavaClassObject(className, kind);
        return jclassObject;
    }
}