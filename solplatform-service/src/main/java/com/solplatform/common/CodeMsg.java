package com.solplatform.common;

import lombok.Data;

/**
 * 常用错误代码及消息封装
 *
 * @author sol
 * @create 2020-01-10  12:06
 */
@Data
public class CodeMsg {

    private int code;
    private String msg;

    /**
     * 私有构造器
     *
     * @param code
     * @param msg
     */
    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通用成功返回
     */
    public static final CodeMsg SUCCESS = new CodeMsg (200, "操作成功");
    /**
     * 通用错误返回
     */
    public static final CodeMsg SERVER_ERROR = new CodeMsg (500, "服务器异常！");
    public static final CodeMsg ERROR = new CodeMsg (400, "请求失败");
    public static final CodeMsg VALIDATE_ERROR = new CodeMsg (401, "暂未登录或token已经过期");
    public static final CodeMsg NOTFOUND_ERROR = new CodeMsg (404, "路径不存在，请检查路径是否正确");
    public static final CodeMsg FORBIDDEN_ERROR = new CodeMsg (403, "没有相关权限");
}
