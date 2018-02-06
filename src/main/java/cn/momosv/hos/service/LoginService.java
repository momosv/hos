package cn.momosv.hos.service;


import cn.momosv.hos.model.TbBaseUserPO;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface LoginService {
    void sendUserRegisterMail(TbBaseUserPO user) throws IOException, TemplateException;
}
