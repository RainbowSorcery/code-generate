package com.lyra.codegenerate.controller;

import com.lyra.codegenerate.enums.SwaggerStatusEnums;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "枚举")
@RequestMapping("/enums")
public class EnumController {
    @GetMapping("/swaggerStatusEnum")
    @Operation(summary = "swagger类型枚举")
    public Map<Object, Object> swaggerStatusEnum() {
        Map<Object, Object> resultMap = new HashMap<>();
        for (SwaggerStatusEnums value : SwaggerStatusEnums.values()) {
            resultMap.put(value.getCode(), value.getDesc());
        }

        return resultMap;
    }

    @GetMapping("/generateTypeEnum")
    @Operation(summary = "代码生成类型")
    public Map<Object, Object> GenerateTypeEnum() {
        Map<Object, Object> resultMap = new HashMap<>();
        for (GenerateType value : GenerateType.values()) {
            resultMap.put(value.getCode(), value.getDesc());
        }

        return resultMap;
    }
}
