package com.lvmoney.yaml;/**
 * 描述:
 * 包名:com.lvmoney.Yaml
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */


import ch.ethz.ssh2.Connection;
import com.lvmoney.k8s.base.util.ExecCmdResult;
import com.lvmoney.k8s.base.util.Ssh2Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 22:23
 */
public class SSHTest {
    private String host = "10.20.128.235";

    private String username = "root";

    private String password = "shch200818";
    private int port = 22;

    private Connection connection;

    // 执行每个单元测试用例前, 创建ssh2连接
    @Before
    public void setup() {
        connection = Ssh2Util.openConnection(host, port, username, password);
    }

    // 执行每个单元测试后, 关闭连接
    @After
    public void tearDown() {
        connection.close();
    }

    /**
     * 测试连接
     */
    @Test
    public void test_connect() {
        System.out.println("hostname:" + connection.getHostname());
    }

    /**
     * 测试执行正确命令
     */
    @Test
    public void test_exec_right() {
        ExecCmdResult execCmdResult = Ssh2Util.execCommand(connection, "kubectl apply -f /root/test/v1-IGateway-provider.Yaml");

        System.out.println("命令是否正确执行:" + execCmdResult.isSuccess());
        System.out.println("命令执行结果:\n" + execCmdResult.getResult());
    }

    /**
     * 测试执行带命令通配符的命令
     */
    @Test
    public void test_exec_ms() {
        ExecCmdResult execCmdResult = Ssh2Util.execCommand(connection, "ls -d /*bin");

        System.out.println("命令是否正确执行:" + execCmdResult.isSuccess());
        System.out.println("命令执行结果:\n" + execCmdResult.getResult());
    }

    /**
     * 测试执行错误命令
     */
    @Test
    public void test_exec_wrong() {
        ExecCmdResult execCmdResult = Ssh2Util.execCommand(connection, "ls /2");

        System.out.println("命令是否正确执行:" + execCmdResult.isSuccess());
        System.out.println("命令执行结果:\n" + execCmdResult.getResult());
    }

    /**
     * 测试下载单个文件
     */
    @Test
    public void test_download() {
        Ssh2Util.download(connection, ".", "/etc/passwd");
    }

    /**
     * 测试批量下载多个文件
     */
    @Test
    public void test_download_batch() {
        Ssh2Util.download(connection, ".", "/etc/passwd", "/bin/bash");
    }

    /**
     * 测试通配符下载多个文件
     */
    @Test
    public void test_download_Parttern() {
        Ssh2Util.downloadByPattern(connection, ".", "/bin", "*m");
    }

    /**
     * 测试单个上传
     */
    @Test
    public void test_upload() {
        Ssh2Util.upload(connection, "/root/test", "D:\\workbench\\idea\\frame\\data\\Yaml\\v1-IDeploy-provider.Yaml");
    }

    /**
     * 测试批量上传
     */
    @Test
    public void test_upload_batch() {
        Ssh2Util.upload(connection, "/tmp/lvmoney/tt", "rm", "udevadm");
    }
}
