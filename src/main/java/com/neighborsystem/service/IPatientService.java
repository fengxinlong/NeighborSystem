package com.neighborsystem.service;

import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Patient;

public interface IPatientService {
	Integer addPatient(Patient patient);

	PageInfo<Patient> findBySplitPage(Integer page, Integer size, String keyword);
}
