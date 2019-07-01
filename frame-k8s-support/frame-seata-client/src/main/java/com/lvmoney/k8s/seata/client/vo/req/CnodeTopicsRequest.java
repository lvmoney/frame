package com.lvmoney.k8s.seata.client.vo.req;

/**
 * Created by Administrator on 2019/5/23.
 */
public class CnodeTopicsRequest {
    private Integer page;
    private String tab;
    private Integer limit;
    private String mdrender;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }


    public String getMdrender() {
        return mdrender;
    }

    public void setMdrender(String mdrender) {
        this.mdrender = mdrender;
    }
}
