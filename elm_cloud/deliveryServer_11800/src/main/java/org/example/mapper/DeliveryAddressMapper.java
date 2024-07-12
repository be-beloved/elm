package org.example.mapper;

import java.util.List;

import example.org.entity.DeliveryAddress;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



@Mapper
public interface DeliveryAddressMapper {

	@Select("select * from deliveryaddress where userId=#{userId} order by daId")
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
	@Select("select * from deliveryaddress where daId=#{daId}")
	public DeliveryAddress getDeliveryAddressById(Integer daId);
	
	@Insert("insert into deliveryaddress values(null,#{contactName},#{contactSex},#{contactTel},#{address},#{userId})")
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
	
	@Update("update deliveryaddress set contactName=#{contactName},contactSex=#{contactSex},contactTel=#{contactTel},address=#{address} where daId=#{daId}")
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
	
	@Delete("delete from deliveryaddress where daId=#{daId}")
	public int removeDeliveryAddress(Integer daId);
}
