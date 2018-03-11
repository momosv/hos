package cn.momosv.hos.service;

import cn.momosv.hos.model.TbContactUsPO;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

public interface SysService {
    void updateMedicalAct(List id, Integer act) throws Exception;

    void updateUserAct(List id, Integer act) throws Exception;

    void reply(TbContactUsPO contact) throws IOException, TemplateException;
}
