package com.neighborsystem.service;

import com.neighborsystem.entity.Areas;
import com.neighborsystem.entity.Cities;
import com.neighborsystem.entity.Provinces;

import java.util.List;

public interface IProCityAreaService {
	List<Provinces> findAllProvinces();
	List<Cities> findCityByProvincesId(String provinceId);
	List<Areas> findAreaByCityId(String cityId);
	Provinces findProvinceById(String id);
	Cities findCityById(String id);
	Areas findAreaById(String id);
	Provinces findProvinceByName(String name);
	Cities findCityByName(String name, String provinceId);
	Areas findAreaByName(String name, String cityId);
}
