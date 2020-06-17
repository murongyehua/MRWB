package com.murongyehua.mrwb.commom;

import com.murongyehua.mrwb.commom.enums.ENMsgCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ResultContext<T> implements Serializable {

    private static final long serialVersionUID = 5856432252782588227L;

    /**
     * 状态码，对应枚举ENMsgCode
     * 0：请求操作成功
     * 1：业务错误
     * 2：系统异常
     * 3：未登录
     * 4：session超时
     * 5：没有权限
     */
    private String code;

    /**
     * 提示信息
     */
    private String info;

    /**
     * 需要返回的数据
     */
    private T data;

    public ResultContext(T data) {
        this();
        setData(data);
    }

    private ResultContext(String code, String info) {
        this.code = code;
        this.info = info;
    }


    public ResultContext(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static ResultContext success(String info) {
        return new ResultContext(ENMsgCode.SUCCESS.getValue(), info);
    }

    public static ResultContext businessFail(String info) {
        return new ResultContext(ENMsgCode.BUSINESS_ERROR.getValue(), info);
    }

    public static ResultContext systemException(String info) {
        return new ResultContext(ENMsgCode.SYSTEM_EXCEPTION.getValue(), info);
    }
}
