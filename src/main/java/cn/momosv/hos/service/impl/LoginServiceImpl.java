package cn.momosv.hos.service.impl;

import cn.momosv.hos.email.MailService;
import cn.momosv.hos.interceptor.UserLoginInterceptor;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.service.LoginService;
import cn.momosv.hos.util.SysUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.IOException;
import java.util.Map;

@Transactional
@Service("LoginService")
public class LoginServiceImpl implements LoginService{

    @Autowired
    FreeMarkerConfig freeMarkerConfig;

    @Autowired
    MailService mailService;

    @Override
    public void sendUserRegisterMail(TbBaseUserPO user) throws IOException, TemplateException {
        Map<String,String> map=new HashedMap();
        map.put("email",user.getEmail());
        map.put(SysUtil.BASE_PATH, UserLoginInterceptor.cloudAddress);
        // 通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfig.getConfiguration().getTemplate("validReg.html");
        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailService.sendHtmlMail(user.getEmail(),"邮箱认证激活",htmlText);
    }
}
