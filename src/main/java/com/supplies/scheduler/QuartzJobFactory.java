package com.supplies.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.supplies.pojo.ScheduleJob;
import com.supplies.utils.TaskUtils;

/**
 * 无状态
 * @author zzq
 * 实现有状态的Job，加注解 @DisallowConcurrentExecution 老版本是实现 StatefulJob接口 在当前版本中已经不推荐使用了
 */
public class QuartzJobFactory implements Job {

	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//System.out.println("有状态任务成功运行");
		/*JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		for (String i : mergedJobDataMap.keySet()) {
			System.out.println(mergedJobDataMap.get(i));
		}*/
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("schedulejob");
		//System.out.println("有状态任务名称 = [" + scheduleJob.getJobName() + "]");
		TaskUtils.invokMethod(scheduleJob);
	}

	
}
