package com.supplies.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplies.dao.TaskScheduleJobMapper;
import com.supplies.pojo.ScheduleJob;
import com.supplies.service.TaskScheduleJobService;
import com.supplies.utils.TaskUtils;

@Service
public class TaskScheduleJobServiceImpl implements TaskScheduleJobService {
	
	private final static Logger log = Logger.getLogger(TaskScheduleJobServiceImpl.class);
	
	@Autowired
	private TaskScheduleJobMapper taskScheduleJobMapper;
	
	@Autowired
	private TaskUtils taskUtils;
	
	@Override
	public List<ScheduleJob> find() {
		return taskScheduleJobMapper.find();
	}

	@Override
	public int save(ScheduleJob job) {
		job.setCreateTime(new Date());
		int success = taskScheduleJobMapper.save(job);
		//是否立即启动  不启动就只保存到数据库
		if(job != null && ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())){
			//新建或修改任务
			taskUtils.saveOrUpdateJob(job);
		}
		return success;
	}

	@Override
	public int update(ScheduleJob scheduleJob) {
		int success = 0;
		//taskUtils.resumeJob(scheduleJob);
		success = taskScheduleJobMapper.update(scheduleJob);
		return success;
	}
	
	
	/*@PostConstruct
	public void init() throws Exception {

		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		// 这里获取任务信息数据
		List<ScheduleJob> jobList = taskScheduleJobMapper.find();
	
		for (ScheduleJob job : jobList) {
			save(job);
		}
	}*/

	

	

	
}
