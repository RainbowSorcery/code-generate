package com.lyra.codegenerate.service;

import com.lyra.codegenerate.domain.entity.TemplateGroup;
import com.lyra.codegenerate.domain.vo.TemplateGroupTreeVO;
import com.lyra.codegenerate.response.Result;

import java.util.List;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/6/12
 * @Description
 */
public interface ITemplateGroupService {

    Result<List<TemplateGroupTreeVO>> templateGroupTree();

    TemplateGroup getById(Long templateGroupId);
}
