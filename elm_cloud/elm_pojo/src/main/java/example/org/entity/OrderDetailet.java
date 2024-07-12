package example.org.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class OrderDetailet implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer odId;
	private Integer orderId;
	private Integer foodId;
	private Integer quantity;
	
	private Food food;
}
