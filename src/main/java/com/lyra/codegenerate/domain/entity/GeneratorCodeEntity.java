package com.lyra.codegenerate.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/5/19
 * @Description
 */
public class GeneratorCodeEntity {
    @Schema(description = "类名称")
    private String className;
    @Schema(description = "代码内容")
    private String content;

    public GeneratorCodeEntity() {
    }

    public GeneratorCodeEntity(String className, String content) {
        this.className = className;
        this.content = content;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
