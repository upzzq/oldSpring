package com.supplies.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.supplies.annotation.Token;

public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession(true).setAttribute("token", UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                    	System.out.println("重复提交了，"+request.getServletPath());
                    	request.setAttribute("error", "请勿重复提交！");
                        return false;
                    }
                    request.getSession(true).removeAttribute("token");
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
	}
	
	
	
	public boolean isRepeatSubmit(HttpServletRequest request){
		String serverToken = (String)request.getSession(true).getAttribute("token");
		if(serverToken == null){
			return true;
		}
		String clientToken = request.getParameter("token");
		if(clientToken == null){
			return true;
		}
		if(!serverToken.equals(clientToken)){
			return true;
		}
		return false;
	}
	
}
