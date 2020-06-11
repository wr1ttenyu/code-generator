package wr1ttenyu.f1nal.study.code.generator.util.common.response;

import lombok.Data;

/**
 * <p>基础返回结果</p>
 */
@Data
public class BaseResponse {

    /**
     * 返回码
     */
    protected int code;
    /**
     * 返回消息
     */
    protected String message;
}
