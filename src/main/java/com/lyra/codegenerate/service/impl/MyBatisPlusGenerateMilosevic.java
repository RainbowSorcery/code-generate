package com.lyra.codegenerate.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lyra.codegenerate.domain.entity.MyBatisEntityFiled;
import com.lyra.codegenerate.domain.dto.MysqlDTO;
import com.lyra.codegenerate.domain.entity.TemplateGroup;
import com.lyra.codegenerate.enums.MySQLJavaTypeMappingEnum;
import com.lyra.codegenerate.enums.SwaggerStatusEnums;
import com.lyra.codegenerate.service.IGenerateService;
import com.lyra.codegenerate.service.ITemplateGroupService;
import com.lyra.codegenerate.utils.FreeMakerUtils;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author qiaolonglong@tracetech.cn
 * @date 2023/5/18
 * @Description
 */
@Service
public class MyBatisPlusGenerateMilosevic implements IGenerateService {
    private static final Logger log = LoggerFactory.getLogger(MyBatisPlusGenerateMilosevic.class);
    @Autowired
    private Configuration configuration;

    @Autowired
    private FreeMakerUtils freeMakerUtils;

    @Autowired
    private ITemplateGroupService templateGroupService;

    @Override
    public String entity(MysqlDTO mysqlDTO) {
        if (mysqlDTO.getTemplateGroupId() == null) {
            throw new RuntimeException("模板类型为空");
        }

        // 1. 连接MySQL
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://" + mysqlDTO.getIp() + ":" + mysqlDTO.getPort() + "/information_schema");
        dataSource.setUsername(mysqlDTO.getUsername());
        dataSource.setPassword(mysqlDTO.getPassword());
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Map<String, Object> templatePramMap = new HashMap<>();

        templatePramMap.put("packageName", mysqlDTO.getPackageName());
        templatePramMap.put("tableName", mysqlDTO.getTableName());
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(mysqlDTO.getTableName()));
        templatePramMap.put("className", className);

        // 设置导入包字段 Date、BigDecimal实体类
        Set<String> importPackageSet = new HashSet<>();
        if (mysqlDTO.getSwaggerStatus().equals(SwaggerStatusEnums.SWAGGER_3.getCode())) {
            importPackageSet.add("import io.swagger.v3.oas.annotations.media.Schema;");
        }

        templatePramMap.put("importPackageList", importPackageSet);


        // 2. 获取表字段
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select TABLE_NAME, COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT, COLUMN_KEY from information_schema.COLUMNS where TABLE_NAME = ? and TABLE_SCHEMA = ?", mysqlDTO.getTableName(), mysqlDTO.getDatabase());


        List<MyBatisEntityFiled> myBatisEntityFileds = new ArrayList<>();

        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> map = maps.get(i);
            MyBatisEntityFiled myBatisEntityFiled = new MyBatisEntityFiled();
            // 3. 获取字段名称 将字段名称下划线转驼峰并首字母大写
            String name = StrUtil.toCamelCase(map.get("COLUMN_NAME").toString());
            myBatisEntityFiled.setGetSetName(StrUtil.upperFirst(name));
            myBatisEntityFiled.setName(name);

            // 4. 设置swagger注解 注解内容为表注释
            List<String> annoantaionList = new ArrayList<>();

            annoantaionList.add("@Schema(description = \"" + map.get("COLUMN_COMMENT") + "\"" + ")");
            if (map.get("COLUMN_KEY").toString().equalsIgnoreCase("PRI")) {
                annoantaionList.add("@Id");
                importPackageSet.add("import org.springframework.data.annotation.Id;");
            }
            myBatisEntityFiled.setAnnotation(annoantaionList);

            // 5. 根据MySQL数据类型与Java数据类型映射表 设置数据类型
            String javaType = MySQLJavaTypeMappingEnum.MySQlTypeToJavaType(map.get("DATA_TYPE").toString());
            myBatisEntityFiled.setType(javaType);

            // 获取包名称
            String packageName = MySQLJavaTypeMappingEnum.javaTypeToPackageName(javaType);
            if (StrUtil.isNotBlank(packageName)) {
                importPackageSet.add(packageName);
            }


            myBatisEntityFileds.add(myBatisEntityFiled);
        }

        templatePramMap.put("filedList", myBatisEntityFileds);
        templatePramMap.put("importPackageSet", importPackageSet);


        try {
            String templateGroupName = getTemplateGroupName(mysqlDTO.getTemplateGroupId());
            return freeMakerUtils.writeToTemplate(configuration, "/templates/" + templateGroupName, "entity.ftl", templatePramMap);
        } catch (Exception e) {
            log.error("实体类生成失败，错误信息:{}", e.getMessage());
            return null;
        }

    }

    private String getTemplateGroupName(Long groupId) {
        List<String> templateGroupList = new ArrayList<>();

        TemplateGroup templateGroup = templateGroupService.getById(groupId);

        templateGroupList.add(templateGroup.getGroupName());
        while (templateGroup.getParentId() != 0) {
            templateGroup = templateGroupService.getById(templateGroup.getParentId());
            templateGroupList.add(templateGroup.getGroupName());
        }

        StringBuilder templateGroupName = new StringBuilder();

        for (int i = templateGroupList.size(); i > 0; i--) {
            templateGroupName.append(templateGroupList.get(i - 1)).append("/");
        }

        log.info("templateGroup:{}", templateGroupName.toString());

        return templateGroupName.toString();
    }

    @Override
    public String dao(MysqlDTO mysqlDTO) {
        if (StrUtil.isBlank(mysqlDTO.getTableName())) {
            throw new RuntimeException("类名称为空");
        }

        Map<String, Object> templatePramMap = new HashMap<>();
        templatePramMap.put("packageName", mysqlDTO.getPackageName());
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(mysqlDTO.getTableName()));
        templatePramMap.put("className", className);

        try {
            String templateGroupName = getTemplateGroupName(mysqlDTO.getTemplateGroupId());

            return freeMakerUtils.writeToTemplate(configuration, "/templates/" + templateGroupName, "dao.ftl", templatePramMap);
        } catch (Exception e) {
            log.error("实体类生成失败，错误信息:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public String service(MysqlDTO mysqlDTO) {
        if (StrUtil.isBlank(mysqlDTO.getTableName())) {
            throw new RuntimeException("类名称为空");
        }

        Map<String, Object> templatePramMap = new HashMap<>();
        templatePramMap.put("packageName", mysqlDTO.getPackageName());
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(mysqlDTO.getTableName()));
        templatePramMap.put("className", className);

        try {
            String templateGroupName = getTemplateGroupName(mysqlDTO.getTemplateGroupId());
            return freeMakerUtils.writeToTemplate(configuration, "/templates/" + templateGroupName, "service.ftl", templatePramMap);
        } catch (Exception e) {
            log.error("实体类生成失败，错误信息:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public String controller(MysqlDTO mysqlDTO) {
        if (StrUtil.isBlank(mysqlDTO.getTableName())) {
            throw new RuntimeException("类名称为空");
        }

        Map<String, Object> templatePramMap = new HashMap<>();
        templatePramMap.put("packageName", mysqlDTO.getPackageName());
        String className = StrUtil.upperFirst(StrUtil.toCamelCase(mysqlDTO.getTableName()));
        templatePramMap.put("className", className);
        templatePramMap.put("subClassName", StrUtil.lowerFirst(className));

        try {
            String templateGroupName = getTemplateGroupName(mysqlDTO.getTemplateGroupId());
            return freeMakerUtils.writeToTemplate(configuration, "/templates/" + templateGroupName, "controller.ftl", templatePramMap);
        } catch (Exception e) {
            log.error("实体类生成失败，错误信息:{}", e.getMessage());
            return null;
        }
    }
}
