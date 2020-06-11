package wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.BusinessException;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.assertion.BusinessExceptionAssert;

/**
 * <p>业务异常</p>
 */
@Getter
@AllArgsConstructor
public enum BusinessExceptionEnum implements BusinessExceptionAssert {

    /**
     * 4***，对应{@link BusinessException}，系统业务处理异常
     */
    USER_NOT_FOUND(4000, "用户不存在"),

    INVAILD_TOKEN(4001, "TOKEN无效"),

    NOT_KNOWN_ORDER_EXECUTOR(4051, "未知订单处理器，请检查"),

    ORDER_EXECUTOR_NOT_FOUND(4052, "订单[{0}] 类型为[{1}] 执行[{2}]周期 处理器未找到，请检查"),

    ORDER_EXECUTOR_NOT_SUPPORT(4053, "订单[{0}] 类型为[{1}] 执行[{2}]周期的操作暂不支持，请谅解"),

    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

}
