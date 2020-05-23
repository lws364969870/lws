package com.lws.gencode.enums;

import lombok.Getter;

@Getter
public enum ModulEnum {

    AUTH("auth", "lws-auth", "com.lws"),
    SYSTEM("system", "lws-system", "com.lws");

    ModulEnum(String moduleName, String url, String pageageName) {
        this.moduleName = moduleName;
        this.url = url;
        this.pageageName = pageageName;
    }

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 物理路径
     */
    private String url;

    /**
     * 包名称
     */
    private String pageageName;

}
