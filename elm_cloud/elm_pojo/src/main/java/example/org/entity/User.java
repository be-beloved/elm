package example.org.entity;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String userId;
    private String password;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;
}
