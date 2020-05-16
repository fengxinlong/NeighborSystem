package com.neighborsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Patient;
import com.neighborsystem.mapper.PatientMapper;
import com.neighborsystem.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements IPatientService{
	@Autowired
	private PatientMapper patientMapper;


	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public Integer addPatient(Patient patient) {
		return patientMapper.addPatient(patient);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public PageInfo<Patient> findBySplitPage(Integer page, Integer size, String keyword) {
		List<Patient> list = new ArrayList<Patient>();
		PageHelper.startPage(page,size);
		if(keyword!=null && !keyword.trim().equals("")){
			list=patientMapper.findPatientLikeName(keyword);
		}else{
			list=patientMapper.findAllPatient();
		}
		PageInfo<Patient> info = new PageInfo<Patient>(list);
		return info;
	}

//	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
//	public PageInfo<Goods> findBySplitPage(Integer page,Integer size,String keyword){
//		List<Goods> list =new ArrayList<Goods>();
//		PageHelper.startPage(page, size);
//		if(keyword!=null &&!keyword.trim().equals("")){
//			list=goodsMapper.findGoodsLikeName(keyword);
//		}else{
//			list = goodsMapper.findAll();
//		}
//		PageInfo<Goods> info=new PageInfo<Goods>(list);
//		return info;
//	}
}
