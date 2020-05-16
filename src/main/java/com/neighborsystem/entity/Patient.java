package com.neighborsystem.entity;

import java.io.Serializable;

/*
* CREATE TABLE patient(

	patient_id INT(11) NOT NULL AUTO_INCREMENT,
	patient_name VARCHAR(32) NOT NULL COMMENT '病人名称',
	patient_sex CHAR(1) DEFAULT NULL COMMENT '性别',
	patient_phone VARCHAR(20),
	patient_state INT(11) NULL DEFAULT 1 COMMENT '1无新冠肺炎 2新冠肺炎疑似 3新冠肺炎确诊',
	one_status CHAR(1) DEFAULT NULL COMMENT '是否发热，呼吸困难',
	two_status CHAR(1) DEFAULT NULL COMMENT '是否有共同接触者',
	patient_Content VARCHAR(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细情况',
	PRIMARY KEY(patient_id)
)
*
*
* */
public class Patient implements Serializable{
	public Patient(){
		super();
	}
	public Patient(Integer patientId,String patientName,String patientSex,String patientPhone,Integer patientState,String oneStatus,String twoStatus,String patientContent){
		super();
		this.patientId=patientId;
		this.patientName=patientName;
		this.patientSex=patientSex;
		this.patientPhone=patientPhone;
		this.patientState=patientState;
		this.oneStatus=oneStatus;
		this.twoStatus=twoStatus;
		this.patientContent=patientContent;

	}


	private Integer patientId;
	private String patientName;
	private String patientSex;
	private String patientPhone;
	private Integer patientState;
	private String oneStatus;
	private String twoStatus;
	private String patientContent;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public Integer getPatientState() {
		return patientState;
	}

	public void setPatientState(Integer patientState) {
		this.patientState = patientState;
	}

	public String getOneStatus() {
		return oneStatus;
	}

	public void setOneStatus(String oneStatus) {
		this.oneStatus = oneStatus;
	}

	public String getTwoStatus() {
		return twoStatus;
	}

	public void setTwoStatus(String twoStatus) {
		this.twoStatus = twoStatus;
	}

	public String getPatientContent() {
		return patientContent;
	}

	public void setPatientContent(String patientContent) {
		this.patientContent = patientContent;
	}

}
