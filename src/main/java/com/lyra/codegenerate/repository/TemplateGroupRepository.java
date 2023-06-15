package com.lyra.codegenerate.repository;

import com.lyra.codegenerate.domain.entity.TemplateGroup;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/6/12
 * @Description
 */
@Repository
public interface TemplateGroupRepository extends ListPagingAndSortingRepository<TemplateGroup, Long> {
    /**
     * 根据parentId查询模板组信息
     * @param parentId 父id
     * @return 模板组
     */
    List<TemplateGroup> findByParentId(Long parentId);

    TemplateGroup findById(Long templateGroupId);
}
