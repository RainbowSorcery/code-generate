package com.lyra.codegenerate.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.lyra.codegenerate.domain.entity.TemplateGroup;
import com.lyra.codegenerate.domain.vo.TemplateGroupTreeVO;
import com.lyra.codegenerate.repository.TemplateGroupRepository;
import com.lyra.codegenerate.response.Result;
import com.lyra.codegenerate.service.ITemplateGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/6/12
 * @Description
 */
@Service
public class TemplateGroupServiceImpl implements ITemplateGroupService {
    @Autowired
    private TemplateGroupRepository templateGroupRepository;

    @Override
    public Result<List<TemplateGroupTreeVO>> templateGroupTree() {
        // 查询parent_id为0的一级目录
        List<TemplateGroupTreeVO> allTemplateGroup = templateGroupRepository.findAll(Sort.unsorted())
                .stream()
                .map(templateGroup -> {
                    TemplateGroupTreeVO templateGroupTreeVO = new TemplateGroupTreeVO();
                    BeanUtils.copyProperties(templateGroup, templateGroupTreeVO);
                    return templateGroupTreeVO;
                })
                .toList();

        List<TemplateGroupTreeVO> parentTemplateGroup = allTemplateGroup
                .stream()
                .filter(templateGroupTreeVO -> templateGroupTreeVO.getParentId() == 0L)
                .toList();

        for (TemplateGroupTreeVO templateGroupTreeVO : parentTemplateGroup) {
            // 根据一级目录遍历其他目录
            getChildren(allTemplateGroup, templateGroupTreeVO);
        }

        return Result.ok(parentTemplateGroup);
    }

    @Override
    public TemplateGroup getById(Long templateGroupId) {
        return templateGroupRepository.findById(templateGroupId);
    }

    private void getChildren(List<TemplateGroupTreeVO> templateGroupList, TemplateGroupTreeVO parent) {
        List<TemplateGroupTreeVO> children = templateGroupList
                .stream()
                .filter(organization -> parent.getId().equals(organization.getParentId()))
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(children)) {
            return;
        }
        parent.setChildren(children);
        for (TemplateGroupTreeVO child : children) {
            getChildren(templateGroupList, child);
        }
    }
}
