package com.lyra.codegenerate.controller;

import com.lyra.codegenerate.domain.dto.MysqlDTO;
import com.lyra.codegenerate.enums.GenerateType;
import com.lyra.codegenerate.response.Result;
import com.lyra.codegenerate.service.IGenerateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate")
@Tag(name = "代码生成器Controller")
@CrossOrigin
public class GenerateController {
    @Autowired
    @Qualifier(value = "myBatisPlusGenerateMilosevic")
    private IGenerateService mybatisPlustGenerateService;

    @PostMapping("/generatorCode")
    @Operation(summary = "生成代码")
    public Result<String> generatorCode(@RequestBody MysqlDTO mysqlDTO, HttpServletResponse response) {
        // todo 可以用策略模式进行优化
        String content = "";
        if (mysqlDTO.getGenerateTypeCode().equals(GenerateType.ENTITY.getCode())) {
            content = mybatisPlustGenerateService.entity(mysqlDTO);
        } else if (mysqlDTO.getGenerateTypeCode().equals(GenerateType.DAO.getCode())) {
            content = mybatisPlustGenerateService.dao(mysqlDTO);
        } else if (mysqlDTO.getGenerateTypeCode().equals(GenerateType.SERVICE.getCode())) {
            content = mybatisPlustGenerateService.service(mysqlDTO);
        } else if (mysqlDTO.getGenerateTypeCode().equals(GenerateType.CONTROLLER.getCode())) {
            content = mybatisPlustGenerateService.controller(mysqlDTO);
        }

        return new Result<>(200, "", true, content);
    }
}
