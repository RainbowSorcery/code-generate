package com.lyra.codegenerate.controller;

import cn.hutool.core.util.StrUtil;
import com.lyra.codegenerate.domain.entity.MyBatisEntityFiled;
import com.lyra.codegenerate.domain.entity.MysqlEntity;
import com.lyra.codegenerate.enums.MySQLJavaTypeMappingEnum;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generate")
@Tag(name = "代码生成器Controller")
public class GenerateController {
    @PostMapping("/mybatisPlus")
    @Operation(summary = "生成MybatisPlus代码")
    public void generatorMysqlSourceCode(@RequestBody MysqlEntity mysqlEntity) {
        // 1. 连接MySQL
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://" + mysqlEntity.getIp() + ":" + mysqlEntity.getPort() + "/information_schema");
        dataSource.setUsername(mysqlEntity.getUsername());
        dataSource.setPassword(mysqlEntity.getPassword());
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Map<String, Object> templatePramMap = new HashMap<>();

        templatePramMap.put("packageName", mysqlEntity.getPackageName());
        templatePramMap.put("tableName", mysqlEntity.getTableName());
        String className = StrUtil.toCamelCase(mysqlEntity.getTableName());
        templatePramMap.put("className", StrUtil.upperFirst(className));

        // 2. 获取表字段
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select TABLE_NAME, COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT, COLUMN_KEY from COLUMNS where TABLE_NAME = ?", mysqlEntity.getTableName());


        List<MyBatisEntityFiled> myBatisEntityFileds = new ArrayList<>();
        templatePramMap.put("filedList", templatePramMap);

        for (Map<String, Object> map : maps) {
            MyBatisEntityFiled myBatisEntityFiled = new MyBatisEntityFiled();
            myBatisEntityFiled.setAnnotation("");
            // 3. 获取表名称设置到model tableName中
            myBatisEntityFiled.setName(StrUtil.toCamelCase(map.get("COLUMN_NAME").toString()));
            // 4. 将表名称下划线转成驼峰设置到className中
            myBatisEntityFiled.setType(MySQLJavaTypeMappingEnum.MySQlTypeToJavaType(map.get("DATA_TYPE").toString()));
            // 5. 获取字段名称 将字段下划线转驼峰设置到filed中

            // 5. 获取字段注释 将注释设置到filed中
            // 6. 获取字段类型 将字段类型设置到filed中
            myBatisEntityFileds.add(myBatisEntityFiled);
        }

        templatePramMap.put("filedList", myBatisEntityFileds);

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        try {
            configuration.setDirectoryForTemplateLoading(new File("D:\\java-project\\code-generate\\src\\main\\resources\\templates\\mybatisplus"));
            configuration.setDefaultEncoding("UTF-8");
            Template template = configuration.getTemplate("entity.ftl");
            template.process(templatePramMap, new FileWriter("d://test.java"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 7. 生成.java文件
        // 8. 调用文件下载
    }
}
