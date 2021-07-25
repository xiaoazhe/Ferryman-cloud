package com.ferry.admin.vo;

import com.ferry.server.admin.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class FaceLoginResult implements Serializable {
    private static final long serialVersionUID = -1616426041373762391L;
    /**
     * 二维码使用状态
     */
    private String state;
    /**
     * 登录信息
     */
    private String token;
    /**
     * 用户ID
     */
    private SysUser user;

    public FaceLoginResult(String state, String token, SysUser user) {
        this.state = state;
        this.token = token;
        this.user = user;
    }

    public FaceLoginResult(String state) {
        this.state = state;
    }
}

