package com.lvmoney.common.util.dynamic;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * @describe： 用于传递源程序
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class JavaClassObject extends SimpleJavaFileObject {

    protected final ByteArrayOutputStream bos =
            new ByteArrayOutputStream();


    public JavaClassObject(String name, JavaFileObject.Kind kind) {
        super(URI.create("string:///" + name.replace('.', '/')
                + kind.extension), kind);
    }


    public byte[] getBytes() {
        return bos.toByteArray();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return bos;
    }
}