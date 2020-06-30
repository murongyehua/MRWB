package com.murongyehua.mrwb.commom.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MRBaseException extends RuntimeException {

    private static final long serialVersionUID = -4432374991625704069L;

    /**
     * 表示异常等级和需要打印的日志等级
     */

    public MRBaseException(String str) {
        super(str);
    }

    public MRBaseException(Throwable ex) {
        super(ex);
    }

    public MRBaseException(String str, Throwable ex) {
        super(str, ex);
    }
}
