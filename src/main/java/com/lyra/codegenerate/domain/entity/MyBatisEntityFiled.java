package com.lyra.codegenerate.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Mybatis plus entity成员变量字段
 */
public class MyBatisEntityFiled {
    @Schema(description = "数据类型")
    private String type;
    @Schema(description = "字段名称")
    private String name;

    @Schema(description = "swagger注解")
    private String annotation;

    @Schema(description = "是否为主键")
    private Boolean pk;

    public Boolean getPk() {
        return pk;
    }

    public void setPk(Boolean pk) {
        this.pk = pk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
