package cn.momosv.hos.model;

import cn.momosv.hos.model.base.IBaseDBPO;
import cn.momosv.hos.util.RegexUtils;

import java.util.Date;

public class TbDataAuthorityPO extends IBaseDBPO{
    @Override
    public String _getTableName() {
        String name= RegexUtils.humpToLine2(this.getClass().getSimpleName());
        name=name.substring(1,name.length()-4);
        return name;
    }

    @Override
    public String _getPKColumnName() {
        return "id";
    }

    @Override
    public String _getPKValue() {
        return id;
    }

    @Override
    public void _setPKValue(Object var1) {
        this.id= (String) var1;
    }
    private String id;

    private String doctorId;

    private String applyDeptId;

    private String applyOrgId;

    private String caseId;

    private String userId;

    private Date createTime;

    private Date deadline;

    private String operator;

    private String caseOrgId;
    private String reason;

    private Integer isAllow;

    private Integer allowGrade;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCaseOrgId() {
        return caseOrgId;
    }

    public void setCaseOrgId(String caseOrgId) {
        this.caseOrgId = caseOrgId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getApplyDeptId() {
        return applyDeptId;
    }

    public void setApplyDeptId(String applyDeptId) {
        this.applyDeptId = applyDeptId == null ? null : applyDeptId.trim();
    }

    public String getApplyOrgId() {
        return applyOrgId;
    }

    public void setApplyOrgId(String applyOrgId) {
        this.applyOrgId = applyOrgId == null ? null : applyOrgId.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
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

    public Integer getAllowGrade() {
        return allowGrade;
    }

    public void setAllowGrade(Integer allowGrade) {
        this.allowGrade = allowGrade;
    }
}