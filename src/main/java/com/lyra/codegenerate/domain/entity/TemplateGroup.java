package com.lyra.codegenerate.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

/**
 * 模板组
 */
@Entity
public class TemplateGroup {
    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "创建者Id")
    private String createUserId;

    @Schema(description = "创建者姓名")
    private String createUserName;

    @Schema(description = "标志 0代表存在 1代表删除")
    private Integer deleted;

    @Schema(description = "组名称")
    private String groupName;

    @Schema(description = "主键")
    @Id
    private Long id;

    @Schema(description = "父id，0表示一级节点")
    private Long parentId;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "更新者Id")
    private String updateUserId;

    @Schema(description = "更新者姓名")
    private String updateUserName;


    public Date  getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String  getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    public String  getCreateUserName() {
        return this.createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    public Integer  getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    public String  getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Long  getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long  getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Date  getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String  getUpdateUserId() {
        return this.updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    public String  getUpdateUserName() {
        return this.updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

}
