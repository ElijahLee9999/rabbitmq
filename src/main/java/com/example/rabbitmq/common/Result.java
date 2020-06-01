package com.example.rabbitmq.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

/**
 * @author Elijah
 * @create 2020-05-28 10:42
 */
@Data
@AllArgsConstructor
@ApiModel(value="Result对象", description="接口返回对象")
public class Result<T> {
    @ApiModelProperty(value = "返回码")
    private int code;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "返回对象")
    private T data;

    public static Result ok(){
        return new Result(0, "success", null);
    }

    public static <T> Result ok(@Nullable T data){
        return new Result(0, "success", data);
    }

    public static Result fail() {
        return new Result(1000, "fail", null);
    }

    public static <T> Result fail(@Nullable T data) {
        return new Result(1000, "fail", data);
    }
}
