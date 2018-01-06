package cn.momosv.hos.model;

import java.util.Date;

public class TbOrgPatientPO {
    private String id;

    private String orgId;

    private String userId;

    private Integer treatCode;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getTreatCode() {
        return treatCode;
    }

    public void setTreatCode(Integer treatCode) {
        this.treatCode = treatCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}