package cn.momosv.hos.service.impl;

import cn.momosv.hos.dao.TbOrgManagerPOMapper;
import cn.momosv.hos.email.MailService;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.model.TbDoctorPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.OrgService;
import cn.momosv.hos.service.UserService;
import cn.momosv.hos.util.SysUtil;
import cn.momosv.hos.vo.TbOrgManagerVO;
import freemarker.template.Template;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("orgService")
public class OrgServiceImpl implements OrgService {

    @Autowired
    TbOrgManagerPOMapper orgMapper;

    @Autowired
    public BasicService basicService;

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    FreeMarkerConfig freeMarkerConfig;

    @Autowired
    MailService mailService;


    @Override
    public boolean validDoctorIdCardExist(String orgId, String idCard){
        return orgMapper.countDoctorByIdCard(orgId,idCard)>0;
    }
    @Override
    public boolean validDoctorEMailExist(String orgId, String email){
        return orgMapper.countDoctorByEmail(orgId,email)>0;
    }
    @Override
    public List<TbDoctorPO> getDoctorByIdCard(String orgId, String idCard){
        return orgMapper.getDoctorByIdCard(orgId,idCard);
    }

    @Override
    public void insertDoctor(TbBaseUserPO user, TbDoctorPO doctor,String deptName) throws Exception {
        //获取baseUser
        TbBaseUserPO oldUser= userService.getUserByIdCard(user.getIdCard());
        Map<String, String> map = new HashedMap();
        if(null != oldUser){
            doctor.setUserId(oldUser.getId());
            map.put("new", "0");
        }else {
            user.setId(UUID.randomUUID().toString());
            user.setAccount(user.getEmail());
            user.setPasswd(doctor.getPasswd());
            user.setName(doctor.getName());
            user.setCreateTime(new Date());
            user.setActCode(1);//默认邮箱认证通过
            basicService.insertOne(user);
            map.put("new", "1");

        }
        doctor.setUserId(oldUser.getId());
        doctor.setAccount(user.getEmail());
        doctor.setCreateTime(new Date());
        doctor.setIsLeave(0);
        basicService.insertOne(doctor);
        //下发邮件
        map.put("email", doctor.getAccount());
        map.put("passwd", doctor.getPasswd());
        map.put("orgName", ((TbOrgManagerVO)session.getAttribute("user")).getOrgName());
        map.put("deptName", deptName);
        map.put("position", doctor.getPosition());
        map.put("name", doctor.getName());
        map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
        // 通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfig.getConfiguration().getTemplate("org/addDoctorEmail.html");
        // 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        mailService.sendHtmlMail(doctor.getAccount(), map.get("orgName")+"人员添加通知", htmlText);

    }
}
