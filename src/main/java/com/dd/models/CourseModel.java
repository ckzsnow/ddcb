package com.dd.models;

import java.sql.Timestamp;

public class CourseModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String brief;
	private String details;
	private Integer industryId;
	private Integer fieldId;
	private Integer stageId;
	private Timestamp schoolTime;
	private String docAttatch;
	private String voiceAttatch;
	private Integer courseType;
	private Integer auditStatus;	
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CourseModel() {
	}

	public CourseModel(Long id, String name, String brief, String details, Integer industryId, Integer fieldId,
			Integer stageId, Timestamp schoolTime, String docAttatch, String voiceAttatch, Integer courseType,
			Integer auditStatus, Timestamp createTime) {
		super();
		this.id = id;
		this.name = name;
		this.brief = brief;
		this.details = details;
		this.industryId = industryId;
		this.fieldId = fieldId;
		this.stageId = stageId;
		this.schoolTime = schoolTime;
		this.docAttatch = docAttatch;
		this.voiceAttatch = voiceAttatch;
		this.courseType = courseType;
		this.auditStatus = auditStatus;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public Timestamp getSchoolTime() {
		return schoolTime;
	}

	public void setSchoolTime(Timestamp schoolTime) {
		this.schoolTime = schoolTime;
	}

	public String getDocAttatch() {
		return docAttatch;
	}

	public void setDocAttatch(String docAttatch) {
		this.docAttatch = docAttatch;
	}

	public String getVoiceAttatch() {
		return voiceAttatch;
	}

	public void setVoiceAttatch(String voiceAttatch) {
		this.voiceAttatch = voiceAttatch;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}