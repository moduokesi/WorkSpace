package com.treat.dto;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author modox
 * @date 2023年6月1日
 * @description 封装结果后返回
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    public static final Integer SUCCESS_CODE = 200;     // 访问成功状态码
    public static final Integer TOKEN_ERROR = 400;      // Token错误状态码
    public static final Integer ERROR_CODE = 500;       // 访问失败状态码
    private Integer status;                               // 状态码
    private String msg;                                 // 提示消息
    private Object data = null;

    public Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static Result ok(Integer status,String msg,Object data){
        return new Result(status,msg,data);
    }

    public static Result ok(String msg,Object data){
        return new Result(SUCCESS_CODE,msg,data);
    }

    public static Result ok(Object data){
        return new Result(SUCCESS_CODE,"操作成功",data);
    }

    public static Result ok(){
        return new Result(SUCCESS_CODE,"操作成功",null);
    }

    public static Result fail(Integer status,String msg){
        return new Result(status,msg);
    }

    public static Result fail(String msg){
        return new Result(ERROR_CODE,msg);
    }

    public static Result fail(){
        return new Result(ERROR_CODE,"操作失败");
    }

    public static Map<String,Object> ok(Map<String,Object> map){
        map.put("status",SUCCESS_CODE);
        map.put("msg","查询成功");
        return map;
    }

    public static Map<String,Object> ok(PageInfo pageInfo){
        Map<String,Object> map = new HashMap<>();
        map.put("status",SUCCESS_CODE);
        map.put("msg","查询成功");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

}
