package com.lyra.codegenerate.controller;

/**
 * 代码生成类型
 */
public enum GenerateType {
    ENTITY(0, "entity"),
    DAO(1, "DAO"),
    SERVICE(2, "service"),
    CONTROLLER(3, "controller");

    GenerateType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
