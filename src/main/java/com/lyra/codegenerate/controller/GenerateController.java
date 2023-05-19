package com.lyra.codegenerate.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.lyra.codegenerate.domain.entity.dto.MysqlDTO;
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

    @PostMapping("/mybatisPlus")
    @Operation(summary = "生成MybatisPlus代码")
    public void generatorMysqlSourceCode(@RequestBody MysqlDTO mysqlDTO, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");

        String entity = mybatisPlustGenerateService.entity(mysqlDTO);
        String dao = mybatisPlustGenerateService.dao();

        String path = UUID.randomUUID().toString();
        File entityFile = new File("code/" + path + "/" + "entity/");
        File daoFile = new File("code/" + path + "/" + "dao/");
        entityFile.mkdirs();
        daoFile.mkdirs();

        FileWriter entityFileWriter = null;
        FileWriter daoFileWriter = null;

        try {
            entityFileWriter = new FileWriter(entityFile);

            entityFileWriter.write(entity);
            entityFileWriter.flush();

            daoFileWriter = new FileWriter(daoFile);
            daoFileWriter.write(dao);
            daoFileWriter.flush();;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (entityFileWriter != null) {
                try {
                    entityFileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (daoFileWriter != null) {
                try {
                    daoFileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        FileInputStream fileInputStream = null;

        try {
            File outPutFile = File.createTempFile("source-code", ".zip");

            File zip = ZipUtil.zip(outPutFile, false, daoFile, entityFile);

            fileInputStream = new FileInputStream(zip);
            byte[] zipBytes = fileInputStream.readAllBytes();

            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(zipBytes);
            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
