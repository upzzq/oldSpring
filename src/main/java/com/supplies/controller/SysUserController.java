package com.supplies.controller;



import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.supplies.annotation.Token;
import com.supplies.common.Constant;
import com.supplies.dao.SysRoleMapper;
import com.supplies.pojo.RetObj;
import com.supplies.pojo.ScheduleJob;
import com.supplies.pojo.SysRole;
import com.supplies.pojo.SysUser;
import com.supplies.scheduler.CustomJob;
import com.supplies.service.SysUserService;
import com.supplies.service.TaskScheduleJobService;
import com.supplies.utils.SpringUtil;
import com.supplies.utils.VerifyCodeUtils;

@Controller
public class SysUserController extends BaseController{
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private TaskScheduleJobService taskScheduleJobService;
	@Autowired
	private SpringUtil appUtil;

	

	@RequestMapping("/login.do")
	public String login(String forceLogout,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("login.do");
		Subject currentUser = SecurityUtils.getSubject();
		//如果用户直接到登录页面 先退出一下
        //原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
        // 这样会导致登录一直死循环
        if (currentUser != null && currentUser.isAuthenticated()) {
        	currentUser.logout();
        }
        String msg = "";
        if(forceLogout != null){
			System.out.println(Constant.ERROR_FORCELOGOUT);
			msg = Constant.ERROR_FORCELOGOUT;
		}else{
			String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
			if(shiroLoginFailure != null){
				if(UnknownAccountException.class.getName().equals(shiroLoginFailure)){
					System.out.println(Constant.ERROR_NO_ACCOUNT);
					msg = Constant.ERROR_NO_ACCOUNT;
				}else if(IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
					System.out.println(Constant.ERROR_PASSWORD);
					msg = Constant.ERROR_PASSWORD;
				}else if(LockedAccountException.class.getName().equals(shiroLoginFailure)){
					System.out.println(Constant.ERROR_USER_LOCKED);
					msg = Constant.ERROR_USER_LOCKED;
				}else if(ExcessiveAttemptsException.class.getName().equals(shiroLoginFailure)){
					System.out.println(Constant.ERROR_TOO_MANY_PASSWORDS);
					msg = Constant.ERROR_TOO_MANY_PASSWORDS;
				}else if(AuthenticationException.class.getName().equals(shiroLoginFailure)){
					System.out.println(Constant.ERROR_USERNAME_OR_PASSWORD);
					msg = Constant.ERROR_USERNAME_OR_PASSWORD;
				}else if("randomCodeError".equals(shiroLoginFailure)){
					System.out.println(Constant.ERROR_YZM);
					msg = Constant.ERROR_YZM;
				}
			}
		}
        request.setAttribute("msg", msg);
		if(currentUser.isAuthenticated()){
			request.getSession().removeAttribute("verCode");
		}
			
		return "login";
	}
		
	
	@RequestMapping("/index.do")
	@Token(save=true)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		WebApplicationContext wac=(WebApplicationContext)request.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);  
	    CustomJob bean = (CustomJob) wac.getBean("job");  
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("users", sysUserService.find());
		request.getSession().setAttribute("user", this.getLoginUser());
		mav.addObject("user", this.getLoginUser());
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		//String url = WebUtils.getSavedRequest(request).getRequestUrl();
		//System.out.println(url);
		return mav;
	}
	
	@RequestMapping("users.do")
	@ResponseBody
	public List<Map<String,Object>> users(HttpServletRequest request,HttpServletResponse response){
		return sysUserService.find();
	}
	@RequestMapping("/logout.do")
	public String logout(){
		SecurityUtils.getSubject().logout();
		//WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
		System.out.println(InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/");
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}
	
	
	@RequestMapping("/yzm.do")
	public void yzm(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
        
        //生成随机字串 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        //存入会话session 
        HttpSession session = request.getSession(true); 
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase()); 
        //生成图片 
        int w = 100, h = 30; 
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
	}

	

	

/*	
	@RequestMapping("/updateUser/{userid}")
	public ModelAndView findUserById(@PathVariable int userid){
		ModelAndView mav = new ModelAndView("userModify");
		mav.addObject("tbuser", userService.selectByPrimaryKey(userid));
		return mav;
	}
	

	@RequestMapping("/rePassword")
	public String updatePwd(String name, String pwd0,String pwd,String pwd1,HttpSession session){
		if(pwd == null || pwd.isEmpty()){
			return "pwsModify";
		}
		TbUser user = (TbUser)session.getAttribute("user");
		if(pwd.equals(pwd1)){
			if(userService.counPwd(user.getUsername(), pwd0) > 0){
				user.setPwd(pwd1);
				userService.updateUser(user);
			}
		}
		return "forward:/userAdd";
	}*/
	@RequestMapping("registration.do")
	public String registration(HttpServletRequest request,HttpServletResponse response){
		return "registration";
	}
	
	@RequestMapping("register.do")
	public String register(HttpServletRequest request,HttpServletResponse response){
		
		return "index";
	}
	
	
	@RequestMapping("/saves.do")
	@Token(remove=true)
	public String  saves(HttpServletRequest request,HttpServletResponse response,SysRole role){
		System.out.println("保存中");
		sysRoleMapper.insert(role);
		return "error";
	}
	
	@RequestMapping("ess.do")
	@RequiresPermissions("item:query")
	public String ess(){
		return "es";
	}
	
	@RequestMapping("uList.do")
	public List<Map<String, Object>> uList(){
		System.out.println("223334343");
		return sysUserService.find();
	}
	
	@RequestMapping("tsh.do")
	public String tsh(){
		return "tsh";
	}
	
}
