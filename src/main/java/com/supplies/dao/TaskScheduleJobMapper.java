package com.supplies.dao;

import java.util.List;

import com.supplies.pojo.ScheduleJob;

public interface TaskScheduleJobMapper {
	
	public List<ScheduleJob> find();
	
	public int save(ScheduleJob scheduleJob);
	
	public int update(ScheduleJob scheduleJob);
}
