package com.neighborsystem.mapper;

import com.neighborsystem.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientMapper {
	/*
	* 查询所有病人
	*
	* */
	List<Patient> findAllPatient();

	/*
	* 添加病人
	* */
	Integer addPatient(Patient patient);


	List<Patient> findPatientLikeName(String name);
	/*
	* 修改病人状态
	*
	* */

	Integer updatePatient(Patient patient);

	/*
	* 根据id查询
	* */
	Patient findPatientById(Integer id);


	/*
	* 删除用户
	*
	* */
	Integer deletePatient(Integer id);

	/*
	* 查询总条数u
	*
	* */
	Integer findTotalPatient();
}
