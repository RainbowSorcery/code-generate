package com.lyra.codegenerate.controller;

import com.lyra.codegenerate.domain.vo.TemplateGroupTreeVO;
import com.lyra.codegenerate.enums.GenerateType;
import com.lyra.codegenerate.enums.SwaggerStatusEnums;
import com.lyra.codegenerate.response.Result;
import com.lyra.codegenerate.service.ITemplateGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "枚举")
@CrossOrigin
@RequestMapping("/enums")
public class EnumController {
    @Autowired
    private ITemplateGroupService templateGroupService;

    @GetMapping("/swaggerStatusEnum")
    @Operation(summary = "swagger类型枚举")
    public Result<Map<Object, Object>> swaggerStatusEnum() {
        Map<Object, Object> resultMap = new HashMap<>();
        for (SwaggerStatusEnums value : SwaggerStatusEnums.values()) {
            resultMap.put(value.getCode(), value.getDesc());
        }

        return Result.ok(resultMap);
    }

    @GetMapping("/generateTypeEnum")
    @Operation(summary = "代码生成类型")
    public Result<Map<Object, Object>> GenerateTypeEnum() {
        Map<Object, Object> resultMap = new HashMap<>();
        for (GenerateType value : GenerateType.values()) {
            resultMap.put(value.getCode(), value.getDesc());
        }

        return Result.ok(resultMap);
    }

    @GetMapping("/templateGroupTree")
    @Operation(summary = "模板类型树")
    private Result<List<TemplateGroupTreeVO>> templateGroupTree() {
        return templateGroupService.templateGroupTree();
    }
}
