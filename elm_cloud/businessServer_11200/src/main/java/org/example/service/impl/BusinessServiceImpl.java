package org.example.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import example.org.dto.PageQueryDTO;
import example.org.entity.Business;
import example.org.result.PageResult;
import jakarta.annotation.Resource;
import org.example.mapper.BusinessMapper;
import org.example.service.BusinessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
	
	@Resource
	private BusinessMapper businessMapper;

	/**
	 * 根据类型获取商家
	 * @param orderTypeId
	 * @return
	 */
	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
		return businessMapper.listBusinessByOrderTypeId(orderTypeId);
	}

	/**
	 * 根据id获取商家
	 * @param businessId
	 * @return
	 */
	@Override
	public Business getBusinessById(Integer businessId) {
		return businessMapper.getBusinessById(businessId);
	}


	/**
	 * 分页查询商家
	 * @param pageQueryDTO
	 * @return
	 */
	@Override
	public PageResult pageQuery(PageQueryDTO pageQueryDTO) {

		PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
		Page<Business>businessPage=businessMapper.pageQuery(pageQueryDTO);

		long total=businessPage.getTotal();
		List<Business>businessList=businessPage.getResult();

		return new PageResult(total,businessList);
	}

	@Override
	public List<Business> getByIdList(List<Integer> idList) {

		return businessMapper.getByIdList(idList);
	}
}
