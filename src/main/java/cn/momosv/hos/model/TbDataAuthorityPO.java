package cn.momosv.hos.model;

import java.util.Date;

public class TbDataAuthorityPO {
    private String id;

    private String applyOrgId;

    private String authorizeOrgId;

    private String userId;

    private Date createTime;

    private String operator;

    private Integer isAllow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplyOrgId() {
        return applyOrgId;
    }

    public void setApplyOrgId(String applyOrgId) {
        this.applyOrgId = applyOrgId == null ? null : applyOrgId.trim();
    }

    public String getAuthorizeOrgId() {
        return authorizeOrgId;
    }

    public void setAuthorizeOrgId(String authorizeOrgId) {
        this.authorizeOrgId = authorizeOrgId == null ? null : authorizeOrgId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(Integer isAllow) {
        this.isAllow = isAllow;
    }
}