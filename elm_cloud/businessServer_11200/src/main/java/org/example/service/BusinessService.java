package org.example.service;

import example.org.dto.PageQueryDTO;
import example.org.entity.Business;
import example.org.result.PageResult;

import java.util.List;


public interface BusinessService {

	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	public Business getBusinessById(Integer businessId);

    PageResult pageQuery(PageQueryDTO pageQueryDTO);

    List<Business> getByIdList(List<Integer> idList);
}
