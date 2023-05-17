package com.lyra.codegenerate.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class MysqlEntity extends BaseEntity {
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
