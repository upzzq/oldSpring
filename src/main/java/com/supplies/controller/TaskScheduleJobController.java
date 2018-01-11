package com.supplies.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.supplies.pojo.RetObj;
import com.supplies.pojo.ScheduleJob;
import com.supplies.pojo.Tas;
import com.supplies.service.TaskScheduleJobService;
import com.supplies.utils.TaskUtils;

@Controller
public class TaskScheduleJobController {
	
	@Autowired
	private TaskScheduleJobService taskScheduleJobService;
	@Autowired
	private TaskUtils taskUtils;

	@RequestMapping("/taskSchedule.do")
	public ModelAndView taskSchedule(){
		ModelAndView mav = new ModelAndView("task_schedule");
		List<ScheduleJob> jobList = taskScheduleJobService.find();
		mav.addObject("tasks",jobList);
		try {
			mav.addObject("allJob",taskUtils.getRunningJob());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 保存任务
	 * @param request
	 * @param response
	 * @param scheduleJob
	 * @return
	 */
	@RequestMapping("/taskAdd.do")
	@ResponseBody
	public RetObj add(HttpServletRequest request,HttpServletResponse response,ScheduleJob scheduleJob){
		WebApplicationContext wac=(WebApplicationContext)request.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		} catch (Exception e) {
			retObj.setMsg("cron表达式有误，不能被解析！");
			return retObj;
		}
		Object obj = null;
		try {
			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
				obj = wac.getBean(scheduleJob.getSpringId());
				if(!obj.getClass().getName().equals(scheduleJob.getBeanClass())){
					retObj.setMsg("springId 找到的类与 classBean 的类不一致");
					return retObj;
				}
			} else {
				Class clazz = Class.forName(scheduleJob.getBeanClass());
				obj = clazz.newInstance();
			}
		}catch(NoSuchBeanDefinitionException ne){
			retObj.setMsg("bean:" + scheduleJob.getSpringId() + ",未注册!");
			return retObj;
		}catch (Exception e) {
			e.printStackTrace();
		}
		Class clazz = obj.getClass();
		Method method = null;
		try {
			method = clazz.getMethod(scheduleJob.getMethodName(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (method == null) {
			retObj.setMsg("未找到目标方法！");
			return retObj;
		}
		try {
			int success = taskScheduleJobService.save(scheduleJob);
			if(success > 0){
				retObj.setFlag(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			retObj.setMsg("保存失败，检查 name group 组合是否有重复！");
		}
		return retObj;
	}
	
	/**
	 * 立即执行
	 * @param request
	 * @param response
	 * @param scheduleJob
	 * @return
	 */
	@RequestMapping("/taskRunAJobNow")
	@ResponseBody
	public RetObj runAJobNow(HttpServletRequest request,HttpServletResponse response,ScheduleJob scheduleJob){
		RetObj retObj = new RetObj();
		int success = taskScheduleJobService.update(scheduleJob);
		return retObj;
	}
	
	
}
