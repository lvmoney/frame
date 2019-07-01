/**
 * 描述:
 * 包名:com.lvmoney.hotel.utils.ro
 * 版本信息: 版本1.0
 * 日期:2018年11月8日  下午5:39:19
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.common.utils.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月8日 下午5:39:19
 */

public class BadiDuMapVoResp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8846539970010752215L;
    private String formatted_address;
    private String country;
    private String province;
    private String citycode;
    private String city;
    private String district;
    private String adcode;
    private String street;
    private String location;
    private String level;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
