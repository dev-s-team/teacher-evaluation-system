package com.csmaxwell.tes.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户登录参数
 * Created by maxwell on 2020/9/14.
 */
public class TesUserLoginParam {

    // @ApiModelProperty(value = "用户名", required = true)
    // @NotEmpty(message = "用户名不能为空")
    // private String username;

    @ApiModelProperty(value = "学号", required = true)
    @NotEmpty(message = "学号不能为空")
    private String no;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
