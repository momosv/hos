package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDepartmentPO;
import cn.momosv.hos.model.TbMedicalOrgPO;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbDoctorVO;
import cn.momosv.hos.vo.TbOrgManagerVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("hos/page")
@Controller
public class PageController extends BasicController {

    @RequestMapping("sys/orgDetail/{id}")
    public String orgDetail(@PathVariable("id") String id,Map map){
      //  map.put("id",id);
        return "sys/orgDetail";
    }
    @RequestMapping("sys/userDetail/{id}")
    public String userDetail(@PathVariable("id") String id,Map map){
      //  map.put("id",id);
        return "sys/userDetail";
    }
    @RequestMapping("org/docDetail/{id}")
    public String docDetail(@PathVariable("id") String id,Map map){
      //  map.put("id",id);
        return "org/docDetail";
    }
    @RequestMapping("org/addDoc")
    public String userDetail(){
        return "org/addDoc";
    }

    @RequestMapping("doc/addCase")
    public String addCase() throws Exception {
        TbDoctorVO doc= validDoctor();
        return "doc/addCase";
    }
    @RequestMapping("doc/caseDetail/{caseId}")
    public String caseDetail(@PathVariable("caseId") String caseId) throws Exception {
        TbDoctorVO doc= validDoctor();
        return "doc/caseDetail";
    }
    @RequestMapping("doc/addSecond/{caseId}/{num}")
    public String addSecond(@PathVariable("caseId") String caseId,@PathVariable("num") String num) throws Exception {
        TbDoctorVO doc= validDoctor();
        if(num.equals("1")){
           return "doc/addReturn";
        }else if(num.equals("2")){
            return "doc/addHospitalized";
        }else if(num.equals("3")){
            return "doc/addSurgery";
        }else if(num.equals("4")){
            return "doc/addAnalyze";
        }else if(num.equals("5")){
            return "doc/addConsultation";
        }else if(num.equals("6")){
            return "doc/addTransfer";
        }else if(num.equals("7")){
            return "doc/addLeaveHos";
        }
        return "login";
    }

    @RequestMapping("doc/getSecond/{caseId}/{secondId}/{num}")
    public String getSecond(@PathVariable("caseId") String caseId,@PathVariable("secondId") String secondId,@PathVariable("num") String num) throws Exception {
        TbDoctorVO doc= validDoctor();
        if(num.equals("1")){
           return "doc/getReturn";
        }else if(num.equals("2")){
            return "doc/getHospitalized";
        }else if(num.equals("3")){
            return "doc/getSurgery";
        }else if(num.equals("4")){
            return "doc/getAnalyze";
        }else if(num.equals("5")){
            return "doc/getConsultation";
        }else if(num.equals("6")){
            return "doc/getTransfer";
        }else if(num.equals("7")){
            return "doc/getLeaveHos";
        }
        return "login";
    }
    @RequestMapping("doc/addSecondReturn/{caseId}/{secondId}/{num}")
    public String addSecondReturn(
            @PathVariable("caseId") String caseId,
            @PathVariable("secondId") String secondId,
            @PathVariable("num") String num) throws Exception {
        TbDoctorVO doc= validDoctor();
        if(num.equals("1")){
           return "doc/addReturn";
        }else if(num.equals("2")){
            return "doc/addHosReturn";
        }else if(num.equals("3")){
            return "doc/addSurReturn";
        }
        return "login";
    }




    @RequestMapping("doc/getSecondReturn/{caseId}/{secondId}/{num}")
    public String getSecondReturn(
            @PathVariable("caseId") String caseId,
            @PathVariable("secondId") String secondId,
            @PathVariable("num") String num) throws Exception {
        TbDoctorVO doc= validDoctor();
        if(num.equals("1")){
           return "doc/getReturn";
        }else if(num.equals("2")){
            return "doc/getHosReturn";
        }else if(num.equals("3")){
            return "doc/getSurReturn";
        }
        return "login";
    }


    @RequestMapping("user/getSecond/{caseId}/{secondId}/{num}")
    public String getUserSecond(@PathVariable("caseId") String caseId,@PathVariable("secondId") String secondId,@PathVariable("num") String num) throws Exception {
        validUser();
        if(num.equals("1")){
            return "user/getReturn";
        }else if(num.equals("2")){
            return "user/getHospitalized";
        }else if(num.equals("3")){
            return "user/getSurgery";
        }else if(num.equals("4")){
            return "user/getAnalyze";
        }else if(num.equals("5")){
            return "user/getConsultation";
        }else if(num.equals("6")){
            return "user/getTransfer";
        }else if(num.equals("7")){
            return "user/getLeaveHos";
        }
        return "login";
    }

    @RequestMapping("user/getSecondReturn/{caseId}/{secondId}/{num}")
    public String getUserSecondReturn(
            @PathVariable("caseId") String caseId,
            @PathVariable("secondId") String secondId,
            @PathVariable("num") String num) throws Exception {
        validUser();
        if(num.equals("1")){
           return "user/getReturn";
        }else if(num.equals("2")){
            return "user/getHosReturn";
        }else if(num.equals("3")){
            return "user/getSurReturn";
        }
        return "login";
    }


    @RequestMapping("user/caseDetail/{caseId}")
    public String userCaseDetail(@PathVariable("caseId") String caseId) throws Exception {
       // validUser();
        return "user/caseDetail";
    }
    @RequestMapping("org/AuthorityDetail/{authId}")
    public String getAuthorityDetail(@PathVariable("authId") String authId) throws Exception {
        validOrgManager();
        return "org/authorityDetail";
    }

    private TbOrgManagerVO validOrgManager() throws Exception {
        try{
            return (TbOrgManagerVO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录机构管理员后再操作");
        }
    }

    private TbBaseUserPO validUser() throws Exception {
        try{
            return (TbBaseUserPO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录个人身份账号后再操作");
        }
    }
    private TbDoctorVO validDoctor() throws Exception {
        try{
            return (TbDoctorVO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录医生身份账号后再操作");
        }
    }
}
