package com.supplies.dwr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;

public class DwrScriptSessionManagerUtil {
	
	 public void init() throws ServletException {    
         
         Container container = ServerContextFactory.get().getContainer();    
     
         ScriptSessionManager manager = container .getBean(ScriptSessionManager.class);    
     
         ScriptSessionListener listener = new ScriptSessionListener() {    
     
             public void sessionCreated(ScriptSessionEvent ev) {    
     
                // HttpSession session = WebContextFactory.get().getSession();    
     
                 //String userId = (String) session.getAttribute("userId");    
            	 String userId = "1";
                 System.out.println(">>>>>>>>>a ScriptSession is created!");    
     
                 ev.getSession().setAttribute("userId", userId);    
     
             }    
     
             public void sessionDestroyed(ScriptSessionEvent ev) {    
     
                 System.out.println(">>>>>>>>a ScriptSession is distroyed");    
     
             }    
     
         };    
     
         manager.addScriptSessionListener(listener);    
     
     }    
}
