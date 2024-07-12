package example.org.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data

public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;
    private String token;
}
