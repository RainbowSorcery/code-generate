package com.lyra.codegenerate.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.lyra.codegenerate.domain.entity.TableInfo;
import com.lyra.codegenerate.domain.entity.dto.MysqlDTO;
import com.lyra.codegenerate.response.Result;
import com.lyra.codegenerate.service.IGenerateService;
import com.lyra.codegenerate.utils.FileUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/generate")
@Tag(name = "代码生成器Controller")
public class GenerateController {
    @Autowired
    @Qualifier(value = "myBatisPlusGnerateSeviceImpl")
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
