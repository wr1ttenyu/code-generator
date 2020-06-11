package wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.assertion.ArgumentExceptionAssert;

/**
 * <p>参数校验异常返回结果</p>
 */
@Getter
@AllArgsConstructor
public enum ArgumentExceptionEnum implements ArgumentExceptionAssert {

    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(6000, "参数校验异常");

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

}
