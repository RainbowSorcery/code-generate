package com.lyra.codegenerate.enums;

public enum SwaggerStatusEnums {
    NO(0, "不使用swagger"),
    SWAGGER_2(1, "swagger2"),
    SWAGGER_3(2, "swagger3");
    private Integer code;
    private String desc;

    SwaggerStatusEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

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
