package com.lyra.codegenerate.domain.entity.dto;

import com.lyra.codegenerate.domain.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public class MysqlDTO extends BaseEntity {
    @Schema(description = "ip")
    private String ip;

    @Schema(description = "端口")
    private Integer port;

    @Schema(description = "MySQL用户名")
    private String username;
    @Schema(description = "MySQL密码")
    private String password;
    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "数据库名称")
    private String database;

    @Schema(description = "模板名称")
    public String templateGroup;

    @Schema(description = "swagger版本 0: 不适应swagger; 1: swagger2; 2: swagger3")
    private Integer swaggerStatus;

    @Schema(description = "生成类型")
    private Integer generateTypeCode;

    public Integer getGenerateTypeCode() {
        return generateTypeCode;
    }

    public void setGenerateTypeCode(Integer generateTypeCode) {
        this.generateTypeCode = generateTypeCode;
    }

    public Integer getSwaggerStatus() {
        return swaggerStatus;
    }

    public void setSwaggerStatus(Integer swaggerStatus) {
        this.swaggerStatus = swaggerStatus;
    }

    public String getTemplateGroup() {
        return templateGroup;
    }

    public void setTemplateGroup(String templateGroup) {
        this.templateGroup = templateGroup;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
