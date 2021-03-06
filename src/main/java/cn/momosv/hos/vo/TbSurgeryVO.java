package cn.momosv.hos.vo;

import cn.momosv.hos.model.TbReturnVisitPO;
import cn.momosv.hos.model.base.IBaseDBPO;
import cn.momosv.hos.util.RegexUtils;

import java.util.Date;
import java.util.List;

public class TbSurgeryVO extends IBaseDBPO {
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

    private String cause;

    private String start;

    private String finding;

    private String process;

    private String treat;

    private String end;

    private String afterTreat;

    private String diagnosis;

    private String attention;

    private String surgeon;

    private Date createTime;

    private List<TbReturnVisitPO> returnList;

    public List<TbReturnVisitPO> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<TbReturnVisitPO> returnList) {
        this.returnList = returnList;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }


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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start == null ? null : start.trim();
    }

    public String getFinding() {
        return finding;
    }

    public void setFinding(String finding) {
        this.finding = finding == null ? null : finding.trim();
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat == null ? null : treat.trim();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end == null ? null : end.trim();
    }

    public String getAfterTreat() {
        return afterTreat;
    }

    public void setAfterTreat(String afterTreat) {
        this.afterTreat = afterTreat == null ? null : afterTreat.trim();
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis == null ? null : diagnosis.trim();
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }

    public String getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(String surgeon) {
        this.surgeon = surgeon == null ? null : surgeon.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}