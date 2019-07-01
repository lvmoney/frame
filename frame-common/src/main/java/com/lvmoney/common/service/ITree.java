package com.lvmoney.common.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Administrator on 2019/5/31.
 */
public interface ITree {
    /**
     * 获取被依赖节点
     *
     * @return
     */
    Serializable getId();

    /**
     * 设置被依赖节点
     *
     * @param id
     */
    void setId(Serializable id);

    /**
     * 获取依赖节点
     *
     * @return
     */
    Serializable getParent();

    /**
     * 设置依赖节点
     *
     * @param parent
     */
    void setParent(Serializable parent);

    /**
     * 获取孩子列表
     *
     * @return
     */
    Collection<? extends ITree> getChildren();

    /**
     * 设置孩子列表
     *
     * @param children
     */
    void setChildren(Collection<? extends ITree> children);
}
