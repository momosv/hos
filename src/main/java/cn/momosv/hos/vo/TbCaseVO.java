package cn.momosv.hos.vo;

public class TbCaseVO {

  //  c.id,title,c.create_time,diagnosis,name,treat_code
    String id;
    String title;
    String creatTime;
    String diagnosis;
    String name;
    String treatCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTreatCode() {
        return treatCode;
    }

    public void setTreatCode(String treatCode) {
        this.treatCode = treatCode;
    }
}
