package cn.momosv.hos.dao;

import cn.momosv.hos.model.*;
import cn.momosv.hos.vo.TbHospitalizedVO;
import cn.momosv.hos.vo.TbSurgeryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface TbDoctorPOMapper {
    TbDoctorPO selectByPrimaryKey(String id);

    @Select("select id ,create_time from tb_hospitalized where case_id=#{caseId} order by create_time desc")
    List<TbHospitalizedVO> getHosList(@Param("caseId") String caseId);


    @Select("select id ,create_time from tb_leave_hospital where case_id=#{caseId} order by create_time desc")
    List<TbLeaveHospitalPO> getLeaveHosList(@Param("caseId") String caseId);

    @Select("select id ,create_time from tb_transfer where case_id=#{caseId} order by create_time desc")
    List<TbTransferPO> getTransferList(@Param("caseId") String caseId);

    @Select("select id ,create_time from tb_return_visit where case_id=#{caseId} and type='1' order by create_time desc")
    List<TbReturnVisitPO> getReturnList(@Param("caseId") String caseId);

    @Select("select id ,create_time from tb_analyze_plan where case_id=#{caseId} order by create_time desc")
    List<TbAnalyzePlanPO> getAnalyzeList(@Param("caseId") String caseId);

    @Select("select id ,create_time from tb_surgery where case_id=#{caseId} order by create_time desc")
    List<TbSurgeryVO> getSurgeryList(@Param("caseId") String caseId);

    @Select("select id ,create_time from tb_consultation where case_id=#{caseId} order by create_time desc")
    List<TbConsultationPO> getConsultationList(@Param("caseId") String caseId);

    @Select("select id ,second_id,create_time from tb_return_visit where case_id=#{caseId} and second_id=#{secondId} and type=#{type} order by create_time desc")
    List<TbSurgeryPO> getReturnSecondList(@Param("caseId") String caseId,@Param("secondId") String secondId,@Param("type") Integer type);
}