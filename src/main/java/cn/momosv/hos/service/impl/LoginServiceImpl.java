package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbMedicalOrgPOMapper;
import cn.momosv.hos.email.MailService;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.service.LoginService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Transactional
@Service("LoginService")
public class LoginServiceImpl  implements LoginService{

    @Autowired
    FreeMarkerConfig freeMarkerConfig;

    @Autowired
    MailService mailService;

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
}
