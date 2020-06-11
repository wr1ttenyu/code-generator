package wr1ttenyu.f1nal.study.code.generator.util.common.exception.assertion;


import wr1ttenyu.f1nal.study.code.generator.util.common.exception.BaseException;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.IResponseEnum;

import java.text.MessageFormat;

public interface CommonExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = getMessage();
        if(args != null && args.length > 0) msg = MessageFormat.format(msg, args);

        return new BaseException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = getMessage();
        if(args != null && args.length > 0) msg = MessageFormat.format(msg, args);

        return new BaseException(this, args, msg, t);
    }

}
