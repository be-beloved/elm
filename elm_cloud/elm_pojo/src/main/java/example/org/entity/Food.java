package example.org.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Food implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer foodId;
	private String foodName;
	private String foodExplain;
	private String foodImg;
	private Double foodPrice;
	private Integer businessId;
	private String remarks;
}
