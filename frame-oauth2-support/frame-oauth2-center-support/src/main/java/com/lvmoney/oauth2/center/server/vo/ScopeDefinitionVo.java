package com.lvmoney.oauth2.center.server.vo;

public class ScopeDefinitionVo extends CommonVo {
    /**
     *
     */
    private static final long serialVersionUID = 2862177859444895431L;
    private String scope;
    /*定义 解释*/
    private String definition;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
