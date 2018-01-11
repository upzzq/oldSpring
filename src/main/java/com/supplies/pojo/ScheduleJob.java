package com.supplies.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * �ƻ�������Ϣ
 * @author Administrator
 *
 */
public class ScheduleJob implements Serializable {

	public static final String STATUS_RUNNING = "1"; //��������
	public static final String STATUS_NOT_RUNNING = "0";//����������
	public static final String CONCURRENT_IS = "1";//��״̬
	public static final String CONCURRENT_NOT = "0";//��״̬
	private int jobId;

	/**
	 * ����ʱ��
	 */
	private Date createTime;

	/**
	 * �޸�ʱ��
	 */
	private Date updateTime;
	/**
	 * ��������
	 */
	private String jobName;
	/**
	 * �������
	 */
	private String jobGroup;
	/**
	 * ����״̬ �Ƿ���������
	 */
	private String jobStatus;
	/**
	 * cron���ʽ
	 */
	private String cronExpression;
	/**
	 * ����
	 */
	private String description;
	/**
	 * ����ִ��ʱ�����ĸ���ķ��� ����+����
	 */
	private String beanClass;
	/**
	 * �����Ƿ���״̬
	 */
	private String isConcurrent;
	/**
	 * spring bean
	 */
	private String springId;
	/**
	 * ������õķ�����
	 */
	private String methodName;

	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getIsConcurrent() {
		return isConcurrent;
	}
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	public String getSpringId() {
		return springId;
	}
	public void setSpringId(String springId) {
		this.springId = springId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public static String getStatusRunning() {
		return STATUS_RUNNING;
	}
	public static String getStatusNotRunning() {
		return STATUS_NOT_RUNNING;
	}
	public static String getConcurrentIs() {
		return CONCURRENT_IS;
	}
	public static String getConcurrentNot() {
		return CONCURRENT_NOT;
	}
	
	
	
}
