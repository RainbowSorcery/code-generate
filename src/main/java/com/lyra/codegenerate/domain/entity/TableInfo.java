package com.lyra.codegenerate.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class TableInfo {
    @Schema(defaultValue = "包名称")
    private String packageName;
    @Schema(defaultValue = "类名称")
    private String className;

    @Schema(description = "类内容")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
