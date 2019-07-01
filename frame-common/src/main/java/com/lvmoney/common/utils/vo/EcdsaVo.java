/**
 * 描述:
 * 包名:com.lvmoney.pay.vo
 * 版本信息: 版本1.0
 * 日期:2018年10月9日  下午6:06:32
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.utils.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月9日 下午6:06:32
 */

public class EcdsaVo implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = -2171545226000703897L;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 明文
     */
    private String plaintext;
    /**
     * 密文
     */
    private String ciphertext;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    @Override
    public String toString() {
        return "EcdsaVo [privateKey=" + privateKey + ", publicKey=" + publicKey + ", plaintext=" + plaintext
                + ", ciphertext=" + ciphertext + "]";
    }

}
