/**
 * 描述:
 * 包名:com.lvmoney.router.ro.req
 * 版本信息: 版本1.0
 * 日期:2018年12月29日  下午1:46:05
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.router.vo.req;

import javax.validation.constraints.NotNull;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年12月29日 下午1:46:05
 */

public class TestReqVo extends CommonReqVo {

    /**
     *
     */
    private static final long serialVersionUID = -6151119901752098771L;

    private int age;
    @NotNull(message = "{testReqVo.name.notBlank}")
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
