package cn.momosv.hos.service;


import cn.momosv.hos.exception.MyException;
import cn.momosv.hos.model.TbBaseUserPO;
import cn.momosv.hos.vo.TbMedicalOrgVO;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

public interface LoginService {
    void sendUserRegisterMail(TbBaseUserPO user) throws IOException, TemplateException;

    List<TbMedicalOrgVO> getLoginOrg(String[] orgId);

    void findPW(String email,String name, String account, String passwd, String type) throws IOException, TemplateException;

    Object registerUser(TbBaseUserPO user) throws Exception;

}
