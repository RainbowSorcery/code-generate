package com.lyra.codegenerate.enums;

/**
 * 模板类型
 */
public enum TemplateStatusEnums {
    MYBATIS_PLUS("mybatisplus", "mybatis plus");
    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    TemplateStatusEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
