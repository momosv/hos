/**  
 * @Title: BasicServiceImpl.java
 * @Package com.yjw.andy.service.Impl
 * @Description: TODO
 * @author 余健文
 * @date 2016年9月19日
 */
package cn.momosv.hos.service.impl;


import cn.momosv.hos.email.MailService;
import cn.momosv.hos.model.TbMedicalOrgPO;
import cn.momosv.hos.model.TbOrgManagerPO;
import cn.momosv.hos.model.TbSysManagerPO;
import cn.momosv.hos.model.base.BasicExample;
import cn.momosv.hos.service.BasicService;
import cn.momosv.hos.service.SysService;
import cn.momosv.hos.util.SysUtil;
import freemarker.template.Template;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Service("sysService")
public class SysServiceImpl implements SysService {

	@Autowired
	private BasicService basicService;

	@Autowired
	FreeMarkerConfig freeMarkerConfig;

	@Autowired
	MailService mailService;
	@Autowired
	HttpSession session;

	@Override
	public void updateMedicalAct(List id, Integer act) throws Exception {
		BasicExample example=new BasicExample(TbMedicalOrgPO.class);
		example.createCriteria().andVarIn("id",id);
		List<TbMedicalOrgPO> mList=basicService.selectByExample(example);
		TbMedicalOrgPO po=new TbMedicalOrgPO();
		po.setActCode(act);
		po.setUpdateTime(new Date());
		basicService.updateByExample(po,example,true);
		if(act.equals(1)) {
			List<TbOrgManagerPO> orgManagerPOS = new ArrayList<>();
			TbOrgManagerPO orgManagerPO;
			TbSysManagerPO sys = (TbSysManagerPO) (session.getAttribute(SysUtil.USER));
			for (TbMedicalOrgPO orgPO : mList) {
				//生成机构超管
				orgManagerPO = new TbOrgManagerPO();
				orgManagerPO.setId(SysUtil.UUID36());
				orgManagerPO.setAccount(orgPO.getEmail());
				orgManagerPO.setGrade(0);
				orgManagerPO.setOrgId(orgPO.getId());
				orgManagerPO.setPasswd(orgPO.getEmail());
				orgManagerPO.setCreator(sys.getName());
				orgManagerPOS.add(orgManagerPO);
				//下发邮件
				Map<String, String> map = new HashedMap();
				map.put("email", orgPO.getEmail());
				map.put("name", orgPO.getName());
				map.put("act", act.toString());
				map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
				// 通过指定模板名获取FreeMarker模板实例
				Template template = freeMarkerConfig.getConfiguration().getTemplate("sys/approve.html");
				// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
				String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
				mailService.sendHtmlMail(orgPO.getEmail(), "跟踪治疗系统机构审批通过通知", htmlText);
			}
			basicService.insertBatch(orgManagerPOS);
			return;
		}
		for (TbMedicalOrgPO orgPO : mList) {
			//下发邮件
			Map<String, String> map = new HashedMap();
			map.put("email", orgPO.getEmail());
			map.put("name", orgPO.getName());
			map.put("act", act.toString());
			map.put(SysUtil.BASE_PATH, (String) session.getAttribute(SysUtil.BASE_PATH));
			// 通过指定模板名获取FreeMarker模板实例
			Template template = freeMarkerConfig.getConfiguration().getTemplate("sys/approve.html");
			// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
			String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			mailService.sendHtmlMail(orgPO.getEmail(), "跟踪治疗系统机构审批不通过通知", htmlText);
		}

	}

}