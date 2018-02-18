package cn.momosv.hos.vo;

import cn.momosv.hos.model.base.IBaseDBPO;
import cn.momosv.hos.util.RegexUtils;

import java.util.Date;

public class TbOrgManagerVO extends IBaseDBPO {
    @Override
    public String _getTableName() {
        return "tb_org_manager";
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

    private String orgId;

    private String account;

    private String passwd;

    private String name;

    private Integer grade;

    private String creator;

    private Date createTime;

    private Date updateTime;

    private String email;

    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
}