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
    private static final String[] IGNORE_URI = {"/upload","/user","/login","/register","/exit","/druid/","/webjars/","/static/","/templates/"};
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
     // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        boolean flag = false;
        String servletPath = request.getServletPath();
        System.out.println("请求路径是: "+servletPath);
        cloudAddress=request.getLocalAddr().toString().equals("10.135.181.103")?cloudAddress:"localhost";
        SysUtil.setBasePath(request,"http://"+cloudAddress+":"+port+contextPath);
        // 检测是否为需要拦截的请求
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                System.out.println("该请求不需要拦截");
                flag = true;
            }

        }
        // 需要拦截处理的请求
        if (!flag) {
           // SysUtil.getSessionUser(request);
            flag = true;
        }
        return flag;


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