package com.lvmoney.common.utils;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/5
 * Copyright xxxx科技有限公司
 */


import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private static final int FILE_BYRE_LENGTH = 8 * 1024;

    /**
     * @describe: multipart 转flle ,待验证
     * @param: [file]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    public static void multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if ("".equals(file) || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }

    }

    /**
     * @describe: File 转 MultipartFile,待验证
     * @param: [file]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    public static void fileToMultipartFile(File file) throws Exception {

        FileInputStream fileInput = new FileInputStream(file);
        MultipartFile toMultipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(fileInput));
        toMultipartFile.getInputStream();

    }

    /**
     * @describe: inputstream 转file,待验证
     * @param: [ins, file]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/5
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, FILE_BYRE_LENGTH)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            LOGGER.error("inputstream 转 file报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.FILE_INPUTSTREAM2FILE_ERROR);
        }
    }

    /**
     * @describe: byte[] 转 inputstream
     * @param: [buf]
     * @return: java.io.InputStream
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/6
     */
    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    /**
     * @describe: OutputStream 转 InputStream,待验证
     * @param: [out]
     * @return: java.io.ByteArrayInputStream
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/6
     */
    public static ByteArrayInputStream parse(OutputStream out) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    public static String file2Base64(byte[] file) {
        String imgString = Base64.encodeBase64String(file);
        return "data:image/JPEG;base64," + imgString;
    }

    /**
     * @describe:
     * @param: [buf, filePath, fileName]
     * @return: void
     * @author： lvmoney /xxxx科技有限公司
     * 2019/3/16
     */
    public static void byte2File(byte[] buf, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            LOGGER.error("流转换成文件报错:{}", e.getMessage());
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
        }
    }


    public static byte[] file2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            LOGGER.error("文件转成成流报错:{}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("文件转成成流报错:{}", e.getMessage());
        }
        return buffer;
    }

    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            LOGGER.error("流转换成文件报错:{}", e.getMessage());
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
        }
    }


}
