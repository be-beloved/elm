package org.example.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import example.org.dto.PageQueryDTO;
import example.org.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface BusinessMapper {

	@Select("select * from business where orderTypeId=#{orderTypeId} order by businessId")
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	
	@Select("select * from business where businessId=#{businessId}")
	public Business getBusinessById(Integer businessId);

	@Select("select * from business  ")
	Page<Business> pageQuery(PageQueryDTO pageQueryDTO);

	List<Business> getByIdList(List<Integer> idList);
}
