package cn.momosv.hos.interceptor;

import cn.momosv.hos.util.SysUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * 用来存储不拦截的路径
     */
    private static final String[] IGNORE_URI = {"/error","/upload","/login","/register","/exit","/druid/","/static/","/templates/"};
    private static final String[] DEAL_URI = {};

    @Value("${server.port}")
    private String port;
//    @Value("${server.address}")
//    private String address;
    @Value("${server.context-path}")
    public static String contextPath;
	
	@Value("${server.cloudAddress}")
	public static String cloudAddress;
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("开始preHandle,判断请求是否需要拦截");
        SysUtil.setBasePath(request,"http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/");
         // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String servletPath = request.getServletPath();
        // 检测是否为需要拦截的请求
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                System.out.println("该请求不需要拦截");
                return true;
            }
        }
        if(null==request.getSession().getAttribute("user")){
              throw  new Exception("nullUser");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        System.out.println("拦截请求之后");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("控制器处理完成之后");
    }
}