package cn.momosv.hos.model;

import cn.momosv.hos.model.base.IBaseDBPO;
import cn.momosv.hos.util.RegexUtils;

import java.util.Date;

public class TbTransferPO   extends IBaseDBPO {
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

    private String caseId;

    private String summary;

    private String phyExam;

    private String diagnosis;

    private String inProcess;

    private String finalDiagnosis;

    private String pathologyReport;

    private String transferReason;

    private Date createTime;

    private String transferOrg;

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

    public String getPhyExam() {
        return phyExam;
    }

    public void setPhyExam(String phyExam) {
        this.phyExam = phyExam == null ? null : phyExam.trim();
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis == null ? null : diagnosis.trim();
    }

    public String getInProcess() {
        return inProcess;
    }

    public void setInProcess(String inProcess) {
        this.inProcess = inProcess == null ? null : inProcess.trim();
    }

    public String getFinalDiagnosis() {
        return finalDiagnosis;
    }

    public void setFinalDiagnosis(String finalDiagnosis) {
        this.finalDiagnosis = finalDiagnosis == null ? null : finalDiagnosis.trim();
    }

    public String getPathologyReport() {
        return pathologyReport;
    }

    public void setPathologyReport(String pathologyReport) {
        this.pathologyReport = pathologyReport == null ? null : pathologyReport.trim();
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason == null ? null : transferReason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTransferOrg() {
        return transferOrg;
    }

    public void setTransferOrg(String transferOrg) {
        this.transferOrg = transferOrg == null ? null : transferOrg.trim();
    }
}