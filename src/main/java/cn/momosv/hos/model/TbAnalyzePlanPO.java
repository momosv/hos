package cn.momosv.hos.model;

import cn.momosv.hos.model.base.IBaseDBPO;
import cn.momosv.hos.util.RegexUtils;

import java.util.Date;

public class TbAnalyzePlanPO extends IBaseDBPO{
    private String id;

    private String caseId;

    private String summary;

    private String preState;

    private String phyExam;

    private String medicalRecord;

    private String treat;

    private String analyzes;

    private String plan;

    private String remark;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getPreState() {
        return preState;
    }

    public void setPreState(String preState) {
        this.preState = preState == null ? null : preState.trim();
    }

    public String getPhyExam() {
        return phyExam;
    }

    public void setPhyExam(String phyExam) {
        this.phyExam = phyExam == null ? null : phyExam.trim();
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord == null ? null : medicalRecord.trim();
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat == null ? null : treat.trim();
    }

    public String getAnalyzes() {
        return analyzes;
    }

    public void setAnalyzes(String analyzes) {
        this.analyzes = analyzes == null ? null : analyzes.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
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
}