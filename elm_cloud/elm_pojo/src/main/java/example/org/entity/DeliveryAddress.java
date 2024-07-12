package example.org.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DeliveryAddress implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer daId; 
	private String contactName;
	private Integer contactSex;
	private String contactTel;
	private String address; 
	private String userId;
}
