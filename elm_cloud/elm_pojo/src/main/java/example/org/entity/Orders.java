package example.org.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class Orders implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer orderId;
	private String userId;
	private Integer businessId;
	private String orderDate;
	private Double orderTotal;
	private Integer daId;
	private Integer orderState;
	
	//多对一
	private Business business;
	//一对多
	private List<OrderDetailet> list;
}
