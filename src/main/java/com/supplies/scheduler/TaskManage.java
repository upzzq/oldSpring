package com.supplies.scheduler;

import java.util.*;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.supplies.pojo.ScheduleJob;

public class TaskManage {
	
	private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	static{
		/*for (int i = 0; i < 5; i++) {
			ScheduleJob job = new ScheduleJob();
			job.setJobId("10001" + i);
			job.setJobName("data_import" + i);
			job.setJobGroup("dataWork");
			job.setJobStatus("1");
			job.setCronExpression("0/5 * * * * ?");
			addJob(job);
		}*/
	}
	
	public static void addJob(ScheduleJob scheduleJob){
		jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
	}
	
	public void test(){
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//List<Scheduler> jobList = Dataw
	}
}
