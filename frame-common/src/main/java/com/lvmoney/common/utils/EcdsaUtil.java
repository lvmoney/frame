package com.lvmoney.common.utils;

import com.lvmoney.common.utils.vo.EcdsaVo;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class EcdsaUtil {
    private static final Logger logger = LoggerFactory.getLogger(EcdsaUtil.class);
    private static final String PROVIDER = "BC";

    /**
     * @param resouce
     * @param factoryType
     * @param privateKey
     * @param signType
     * @return 2018年10月10日下午1:51:55
     * @describe:通过密钥加密
     * @author: lvmoney /xxxx科技有限公司
     */
    public static String getEcdsaSign(String resouce, String factoryType, String privateKey,
                                      String signType) {
        try {
            PrivateKey pri = buildEcPrivateKey(privateKey, factoryType);
            Signature signature = Signature.getInstance(signType);
            signature.initSign(pri);
            signature.update(resouce.getBytes());
            byte[] result = signature.sign();
            return Hex.encodeHexString(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }


    /**
     * @param res         明文
     * @param sign        私钥加密后的密文
     * @param factoryType 工厂类型
     * @param publicKey   公钥
     * @param signType    加密类型
     * @return 2018年10月10日下午1:47:03
     * @describe:公钥校验
     * @author: lvmoney /xxxx科技有限公司
     */
    public static boolean verifyEcdsa(String res, String sign, String factoryType, String publicKey,
                                      String signType) {
        try {
            PublicKey pubKey = bulidEcPublicKey(publicKey, factoryType);
            Signature signature = Signature.getInstance(signType);
            signature.initVerify(pubKey);
            signature.update(res.getBytes());
            byte[] result = StringUtil.hexToByte(sign);
            boolean bool = signature.verify(result);
            return bool;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }


    /**
     * 获取私钥
     *
     * @param content
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static PrivateKey buildEcPrivateKey(String content, String algorithm) throws Exception {
        byte[] asBytes = Base64Util.decode(content);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(spec);
    }


    /**
     * 获取公钥
     *
     * @param content   文件存储的key
     * @param algorithm factory Type
     * @return
     * @throws Exception
     */
    public static PublicKey bulidEcPublicKey(String content, String algorithm) throws Exception {
        byte[] asBytes = Base64Util.decode(content);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(asBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return (ECPublicKey) keyFactory.generatePublic(spec);
    }

    /**
     * @param factoryType
     * @return 2018年10月10日下午2:09:26
     * @describe:获得密钥和公钥
     * @author: lvmoney /xxxx科技有限公司
     */
    public static EcdsaVo getSecretKey(String factoryType) {
        EcdsaVo result = new EcdsaVo();
        try {
            KeyPairGenerator keyPairGenerator;
            keyPairGenerator = KeyPairGenerator.getInstance(factoryType);
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey pubKey = keyPair.getPublic();
            PrivateKey priKey = keyPair.getPrivate();
            String pubContent = Base64Util.encode(pubKey.getEncoded());
            String priContent = Base64Util.encode(priKey.getEncoded());
            result.setPublicKey(pubContent);
            result.setPrivateKey(priContent);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String encrypt(String res, String publicKey, String factoryType) {
        Cipher cipher;
        try {
            PublicKey pubKey = bulidEcPublicKey(publicKey, factoryType);
            byte[] content = res.getBytes();
            cipher = Cipher.getInstance("ECIES", PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Hex.encodeHexString(cipher.doFinal(content));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


}
