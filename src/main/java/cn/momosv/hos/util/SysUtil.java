package cn.momosv.hos.util;//package cn.momosv.util;

import cn.momosv.hos.model.TbBaseUserPO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class SysUtil {
	 public static final String USER = "user";
	 public static final String BASE_PATH = "basePath";

	    //① 获取保存在Session中的用户对象
	   /* public static TbUserBasePO getSessionUser(HttpServletRequest request) throws LoginException {
	    	TbUserBasePO userBasePO = (TbUserBasePO) request.getSession().getAttribute("USER");
	    	if(userBasePO==null){
	    		throw new LoginException("用户不存在,请登录注册再使用");
	    	}
	    	return userBasePO;
	    }*/

	     //②将用户对象保存到Session中
	    public static void setSessionUser(HttpServletRequest request,TbBaseUserPO TbUserBasePO) {
	        request.getSession().setAttribute(USER,TbUserBasePO);
	    }
	  //②将用户对象保存到Session中
	    public static void removeSessionUser(HttpSession session) {
	       if (session.getAttribute(USER) != null) {
				session.removeAttribute(USER);// 将用户信息移除session
			}
	    }

	    //③ 获取基于应用程序的url绝对路径
	    public static final String getBasePath(HttpServletRequest request) {
	        return (String)request.getSession().getAttribute(BASE_PATH);
	    }
	  //③ 设置基于应用程序的url绝对路径
	    public static final void setBasePath(HttpServletRequest request, String url) {
	    	 request.getSession().setAttribute(BASE_PATH,url);
	    }
}
