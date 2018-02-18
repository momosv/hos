package cn.momosv.hos.service;


import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.vo.TbMedicalOrgVO;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

public interface LoginService {
    void sendUserRegisterMail(TbBaseUserPO user) throws IOException, TemplateException;

    List<TbMedicalOrgVO> getLoginOrg(String[] orgId);
}
