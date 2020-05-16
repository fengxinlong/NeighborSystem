package com.neighborsystem.service.impl;

import com.neighborsystem.entity.Memory;
import com.neighborsystem.mapper.MemoryMapper;
import com.neighborsystem.service.IMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemoryServiceImpl implements IMemoryService {
	@Autowired
	private MemoryMapper memoryMapper;
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Memory> finAllMemory() {
		return memoryMapper.findAllMemory();
	}

}
