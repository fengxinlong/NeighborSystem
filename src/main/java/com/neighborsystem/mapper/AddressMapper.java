package com.neighborsystem.mapper;

import com.neighborsystem.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {
	Integer addAddress(Address addr);
	Integer deleteAddr(Integer addrId);
	Integer updateAddr(Address addr);
	Address findAddrById(Integer id);
	List<Address> findAddrByUserId(Integer userId);
}
