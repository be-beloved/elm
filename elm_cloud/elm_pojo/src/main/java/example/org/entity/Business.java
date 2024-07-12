package example.org.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Business implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        private Integer businessId;
        private String businessName;
        private String businessAddress;
        private String businessExplain;
        private String businessImg;
        private Integer orderTypeId;
        private Double starPrice;
        private Double deliveryPrice;
        private String remarks;
}
