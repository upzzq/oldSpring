package com.supplies.controller;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.supplies.common.Constant;

@Controller
public class SessionController {

	@Autowired
	private SessionDAO sessionDAO;
	
	@RequestMapping("/sessions.do")
	public String sessions(Model model){
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		System.out.println(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		for (Session s : sessions) {
			System.out.println(s.getClass().getName());
			System.out.println(s.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
		}
		model.addAttribute("sessions", sessions);
		model.addAttribute("sessionCount", sessions.size());
		model.addAttribute("sessionk", DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		return  "session_list";
	}
	
	@RequestMapping("/forcelogout.do")
	public String forcelogout(String sessionId,RedirectAttributes redirectAttributes){
		Session session = sessionDAO.readSession(sessionId);
		if(session != null){
			session.setAttribute(Constant.SESSION_FORCE_LOGOUT_KEY, true);
		}
		redirectAttributes.addFlashAttribute("msg", "强制退出成功!");
		return "redirect:/sessions.do";
	}
	
}
