package cn.momosv.hos.service;

import java.util.List;

public interface SysService {
    void updateMedicalAct(List id, Integer act) throws Exception;

    void updateUserAct(List id, Integer act) throws Exception;
}
