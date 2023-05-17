package com.lyra.codegenerate.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class BaseEntity {
    @Schema(description = "包名称")
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
