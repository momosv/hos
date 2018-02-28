package cn.momosv.hos.controller;

import cn.momosv.hos.controller.base.BasicController;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbCasePO;
import cn.momosv.hos.model.TbOrgPatientPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.BasicExample.Criteria;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.UserService;
import cn.momosv.hos.util.SysUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController extends BasicController{


    @Autowired
    UserService userService;

    public Object getCaseList(String title,
                              @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(name="pageSize",defaultValue = "10")int pageSize) throws Exception {
        TbBaseUserPO userPO=validUser();
        BasicExample example=new BasicExample(TbCasePO.class);
        Criteria criteria=example.createCriteria();
        if(!StringUtils.isEmpty(title)){
            criteria.andVarLike("title","%"+title+"%");
        }
        List<String> patientId = userService.getPatientIdListByIdCard(userPO.getIdCard());
        criteria.andVarIn("patient_id",patientId);
        Page page = PageHelper.startPage(pageNum, pageSize);
        basicService.selectByExample(example);
        return Msg.success().add("page",new PageInfo(page.getResult()));
    }

    private TbBaseUserPO validUser() throws Exception {
        try{
            return (TbBaseUserPO)session.getAttribute(SysUtil.USER);
        }
        catch (Exception e){
            throw new Exception("非法请求，请登录个人身份账号后再操作");
        }
    }

}
