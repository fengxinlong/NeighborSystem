package com.neighborsystem.service;

import com.neighborsystem.entity.Address;
import com.neighborsystem.entity.Areas;
import com.neighborsystem.entity.Cities;
import com.neighborsystem.entity.Provinces;

import java.util.List;

public interface IAddressService {
	List<Address> findAddressByUserId(Integer userId);
	Address findAddresById(Integer id);
	Provinces findProByProByName(String name);
	Cities findCityByCityName(String name, String provinceId);
	Areas findAreaByAreaName(String name, String cityId);
	Integer addAddress(Address addr);
	Integer updateAddress(Address addr);
	Integer deleteAddress(Integer addrId);
}
