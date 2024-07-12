package example.org.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

//加入购物车的商品
@Data
public class Cart implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer cartId;
	private Integer foodId;
	private Integer businessId;
	private String userId;
	private Integer quantity;
	
	private Food food;
	private Business business;

}
