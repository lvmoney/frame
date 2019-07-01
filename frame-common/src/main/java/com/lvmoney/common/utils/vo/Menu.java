package com.lvmoney.common.utils.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/5/31.
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
