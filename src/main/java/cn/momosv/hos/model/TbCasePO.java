package cn.momosv.hos.model;

import cn.momosv.hos.model.base.IBaseDBPO;
import cn.momosv.hos.util.RegexUtils;

import java.util.Date;

public class TbCasePO extends IBaseDBPO{
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

    private String patientId;

    private String orgId;

    private String deptId;

    private String doctorId;

    private String complaint;

    private String preHistory;

    private String pastHistory;

    private String familyHistory;

    private String surgicalHistory;

    private String phyExam;

    private String medicalRecord;

    private String diagnosis;

    private String treat;

    private String remark;

    private String inpatientArea;

    private String bedNum;

    private String fromCaseId;

    private String fromDeptName;

    private String toDeptName;

    private String fromOrg;

    private String toOrg;

    private Date createTime;

    private Date updateTime;

    private Integer isArchived;

    private String archiveRemark;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint == null ? null : complaint.trim();
    }

    public String getPreHistory() {
        return preHistory;
    }

    public void setPreHistory(String preHistory) {
        this.preHistory = preHistory == null ? null : preHistory.trim();
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory == null ? null : pastHistory.trim();
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory == null ? null : familyHistory.trim();
    }

    public String getSurgicalHistory() {
        return surgicalHistory;
    }

    public void setSurgicalHistory(String surgicalHistory) {
        this.surgicalHistory = surgicalHistory == null ? null : surgicalHistory.trim();
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis == null ? null : diagnosis.trim();
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat == null ? null : treat.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getInpatientArea() {
        return inpatientArea;
    }

    public void setInpatientArea(String inpatientArea) {
        this.inpatientArea = inpatientArea == null ? null : inpatientArea.trim();
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum == null ? null : bedNum.trim();
    }

    public String getFromCaseId() {
        return fromCaseId;
    }

    public void setFromCaseId(String fromCaseId) {
        this.fromCaseId = fromCaseId == null ? null : fromCaseId.trim();
    }

    public String getFromDeptName() {
        return fromDeptName;
    }

    public void setFromDeptName(String fromDeptName) {
        this.fromDeptName = fromDeptName == null ? null : fromDeptName.trim();
    }

    public String getToDeptName() {
        return toDeptName;
    }

    public void setToDeptName(String toDeptName) {
        this.toDeptName = toDeptName == null ? null : toDeptName.trim();
    }

    public String getFromOrg() {
        return fromOrg;
    }

    public void setFromOrg(String fromOrg) {
        this.fromOrg = fromOrg == null ? null : fromOrg.trim();
    }

    public String getToOrg() {
        return toOrg;
    }

    public void setToOrg(String toOrg) {
        this.toOrg = toOrg == null ? null : toOrg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Integer isArchived) {
        this.isArchived = isArchived;
    }

    public String getArchiveRemark() {
        return archiveRemark;
    }

    public void setArchiveRemark(String archiveRemark) {
        this.archiveRemark = archiveRemark == null ? null : archiveRemark.trim();
    }
}