package wr1ttenyu.f1nal.study.code.generator.util.common.exception.assertion;

import wr1ttenyu.f1nal.study.code.generator.util.common.exception.BaseException;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.BusinessException;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.IResponseEnum;

import java.text.MessageFormat;

/**
 * <p>业务异常断言</p>
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = getMessage();
        if(args != null && args.length > 0) msg = MessageFormat.format(msg, args);

        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = getMessage();
        if(args != null && args.length > 0) msg = MessageFormat.format(msg, args);

        return new BusinessException(this, args, msg, t);
    }

}
