package com.lyra.codegenerate.enums;

import java.math.BigDecimal;

/**
 * MySQL数据类型与Java数据类型映射枚举
 */
public enum MySQLJavaTypeMappingEnum {
    VARCHAR("VARCHAR", "String"),
    CHAR("CHAR", "String"),
    VARCHAR2("VARCHAR2", "String"),
    NVARCHAR("NVARCHAR", "String"),
    LONGNVARCHAR("LONGNVARCHAR", "String"),
    TEXT("TEXT", "String"),
    CLOB("CLOB", "String"),
    TINYLOB("TINYLOB", "String"),
    INT("INT", "Integer"),
    INTEGER("INTEGER", "Integer"),
    SMALLINT("SMALLINT", "Integer"),
    TINYINT("TINYINT", "Integer"),
    BIGINT("BIGINT", "Long"),
    NUMBERIC("NUMBERIC", "Long"),
    NUMBER("NUMBER", "Long"),
    DOUBLE("DOUBLE", "Double"),
    FLOAT("FLOAT", "Float"),
    DATE("DATE", "Date"),
    DATETIME("DATETIME", "Date"),//java.util.Date
    decimal("DECIMAL", "BigDecimal"),
    TIMESTAMP("TIMESTAMP", "Timestamp");//java.sql.Timestamp

    public static String MySQlTypeToJavaType(String mySQLType) {
        for (MySQLJavaTypeMappingEnum value : MySQLJavaTypeMappingEnum.values()) {
            if (value.mySQLType.equalsIgnoreCase(mySQLType)) {
                return value.javaType;
            }
        }

        return null;
    }

    private String mySQLType;
    private String javaType;


    MySQLJavaTypeMappingEnum(String mySQLType, String javaType) {
        this.mySQLType = mySQLType;
        this.javaType = javaType;
    }

    public String getMySQLType() {
        return mySQLType;
    }

    public void setMySQLType(String mySQLType) {
        this.mySQLType = mySQLType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
