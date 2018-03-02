package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbDepartmentPO;
import cn.momosv.hos.model.TbMedicalOrgPO;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbDoctorVO;
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

        }else if(num.equals("4")){

        }else if(num.equals("5")){

        }else if(num.equals("6")){

        }
        return "login";
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
