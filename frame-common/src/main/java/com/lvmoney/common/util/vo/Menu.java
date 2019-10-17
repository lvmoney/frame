package com.lvmoney.common.util.vo;

import lombok.Data;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class Menu {
    private Integer id;
    private Integer parent;
    private List<Menu> children;

    public Menu() {
    }

    public Menu(Integer id, Integer parent) {
        this.id = id;
        this.parent = parent;
    }
}
