package com.lyra.codegenerate.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Mybatis plus entity成员变量字段
 */
public class MyBatisEntityFiled {
    @Schema(description = "数据类型")
    private String type;
    @Schema(description = "字段名称")
    private String name;

    @Schema(description = "注解")
    private List<String> annotation;

    @Schema(description = "是否为主键")
    private Boolean pk;

    @Schema(description = "getSet字段名称")
    private String getSetName;

    public MyBatisEntityFiled() {
    }

    public MyBatisEntityFiled(String type, String name, List<String> annotation, Boolean pk, String getSetName) {
        this.type = type;
        this.name = name;
        this.annotation = annotation;
        this.pk = pk;
        this.getSetName = getSetName;
    }

    public String getGetSetName() {
        return getSetName;
    }

    public void setGetSetName(String getSetName) {
        this.getSetName = getSetName;
    }

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

    public List<String> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(List<String> annotation) {
        this.annotation = annotation;
    }
}
