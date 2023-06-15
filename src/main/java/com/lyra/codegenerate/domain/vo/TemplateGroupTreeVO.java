package com.lyra.codegenerate.domain.vo;

import com.lyra.codegenerate.domain.entity.TemplateGroup;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/6/12
 * @Description 模板组VO
 */
public class TemplateGroupTreeVO extends TemplateGroup {
    @Schema(description = "子对象")
    private List<TemplateGroupTreeVO> children;

    public List<TemplateGroupTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<TemplateGroupTreeVO> children) {
        this.children = children;
    }
}
