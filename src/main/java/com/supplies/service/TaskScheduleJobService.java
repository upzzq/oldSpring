package com.supplies.service;

import java.util.List;
import java.util.Map;

import com.supplies.pojo.ScheduleJob;


public interface TaskScheduleJobService {
	
	public List<ScheduleJob> find();
	
	public int save(ScheduleJob scheduleJob);
	
	public int update (ScheduleJob scheduleJob);
}
