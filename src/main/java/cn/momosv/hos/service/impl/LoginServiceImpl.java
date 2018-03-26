package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbMedicalOrgPOMapper;
import cn.momosv.hos.email.MailService;
import cn.momosv.hos.exception.MyException;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.model.base.Msg;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.LoginService;
import cn.momosv.hos.util.Constants;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbMedicalOrgVO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional
@Service("LoginService")
public class LoginServiceImpl  implements LoginService{

    @Autowired
    FreeMarkerConfig freeMarkerConfig;

    @Autowired
    MailService mailService;

    @Autowired
    BasicService basicService;

    @Value("${server.port}")
    private String port;

    @Value("${server.contextPath}")
    public static String contextPath;

    @Value("${cloudAddress}")
    public static String cloudAddress;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    TbMedicalOrgPOMapper TbMedicalOrgPOMapper;

    @Override
    public void sendUserRegisterMail(TbBaseUserPO user) throws IOException, TemplateException {
        Map<String,String> map=new HashedMap();
        map.put("email",user.getEmail());
        map.put("id",user.getId());
        map.put("userName", (String) session.getAttribute("momo"));
        map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
        // 通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfig.getConfiguration().getTemplate("validReg.html");
        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailService.sendHtmlMail(user.getEmail(),"邮箱认证激活",htmlText);
    }

    @Override
    public List<TbMedicalOrgVO> getLoginOrg(String[] orgId) {
        return TbMedicalOrgPOMapper.getLoginOrg(orgId);
    }


    @Override
    public void findPW(String email,String name, String account, String passwd, String type) throws IOException, TemplateException {
        Map<String,String> map=new HashedMap();
        map.put("name",name);
        map.put("email",account);
        map.put("passwd", passwd);
        map.put("type", type);
        map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
        // 通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfig.getConfiguration().getTemplate("findPWEmail.html");
        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailService.sendHtmlMail(email,"【病人跟踪治疗信息管理系统】找回密码",htmlText);
    }

    @Override
    public Object registerUser(TbBaseUserPO user) throws Exception {
        user.setAccount(user.getEmail());
        TbBaseUserPO otherUser=null;
        if(StringUtils.isEmpty(user.getEmail())){
            throw new MyException("邮箱不能为空");
        }
        if(!StringUtils.isEmpty(user.getEmail())){
            BasicExample uexample=new BasicExample(TbBaseUserPO.class);
            uexample.createCriteria().andVarEqualTo("email",user.getEmail());
            otherUser= (TbBaseUserPO) basicService.selectOneByExample(uexample);
            if(otherUser!=null&&(!otherUser.getActCode().equals(0)&&!otherUser.getActCode().equals(1))){
                throw new MyException("注册失败，该邮箱已经被注册且验证,可以直接登录");
            }
        }

        if(StringUtils.isEmpty(user.getIdCard())){
            return  Msg.fail("注册身份证号不能为空");
        }
        //检查是否有认证过得身份证
        BasicExample example=new BasicExample(TbBaseUserPO.class);
        example.createCriteria().andVarEqualTo("id_card",user.getIdCard());
        TbBaseUserPO userPO = (TbBaseUserPO) basicService.selectOneByExample(example);
        if(otherUser!=null&&!otherUser.getIdCard().equals(user.getIdCard())){//移除已有的同email未身份认证的邮箱账号
            otherUser.setActCode(0);
            otherUser.setAccount("_null");
            otherUser.setEmail("_null");
            basicService.updateOne(otherUser);
        }
        if(null!=userPO){
            if(userPO.getActCode().equals(Constants.USER_PASSED)||(userPO.getActCode().equals(Constants.USER_UN_APPROVED))){
                return Msg.fail(200,"注册失败，身份信息已经被注册认证或处于待审批认证中");
            }
            userPO.setCreateTime(new Date());
            user.setActCode(0);
            user.setId(userPO.getId());
            basicService.updateOne(user,true);
            this.sendUserRegisterMail(userPO);
            return  Msg.success("注册成功");

        }
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setActCode(0);
        basicService.insertOne(user);
        this.sendUserRegisterMail(user);
        return Msg.success("注册成功");
    }
}
