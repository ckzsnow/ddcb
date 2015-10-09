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
	private String industryName;
	private String fieldName;
	private String stageName;
	private Timestamp schoolTime;
	private String formatSchoolTime;
	private String docAttatch;
	private String voiceAttatch;
	private Integer courseType;
	private String courseTypeName;
	private Integer auditStatus;
	private String courseAuditStatusName;
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

	public String getFormatSchoolTime() {
		return formatSchoolTime;
	}

	public void setFormatSchoolTime(String formatSchoolTime) {
		this.formatSchoolTime = formatSchoolTime;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCourseAuditStatusName() {
		return courseAuditStatusName;
	}

	public void setCourseAuditStatusName(String courseAuditStatusName) {
		this.courseAuditStatusName = courseAuditStatusName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
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

	@Override
	public String toString() {
		return "CourseModel [id=" + id + ", name=" + name + ", brief=" + brief + ", details=" + details
				+ ", industryId=" + industryId + ", fieldId=" + fieldId + ", stageId=" + stageId + ", industryName="
				+ industryName + ", fieldName=" + fieldName + ", stageName=" + stageName + ", schoolTime=" + schoolTime
				+ ", formatSchoolTime=" + formatSchoolTime + ", docAttatch=" + docAttatch + ", voiceAttatch="
				+ voiceAttatch + ", courseType=" + courseType + ", courseTypeName=" + courseTypeName + ", auditStatus="
				+ auditStatus + ", courseAuditStatusName=" + courseAuditStatusName + ", createTime=" + createTime + "]";
	}
}