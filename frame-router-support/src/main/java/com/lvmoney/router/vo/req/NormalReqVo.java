/**
 * 描述:
 * 包名:com.lvmoney.router.ro.req
 * 版本信息: 版本1.0
 * 日期:2019年1月2日  上午10:10:21
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.vo.req;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月2日 上午10:10:21
 */

public class NormalReqVo extends CommonReqVo {

    /**
     *
     */
    private static final long serialVersionUID = -6151119901752098771L;

    private int age;
    private String name;
    private String address;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}