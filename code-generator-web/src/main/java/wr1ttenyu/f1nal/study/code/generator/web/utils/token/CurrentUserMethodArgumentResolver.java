package wr1ttenyu.f1nal.study.code.generator.web.utils.token;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums.BusinessExceptionEnum;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String USER = "user";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // 检查该解析器是否支持参数类型 ，如果方法的参数里有 参数类型  SayHiModel 则返回true
        if (methodParameter.getParameterType().equals(UserModel.class)) return true;
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //从request 中获取user信息
        UserModel userModel = TokenManager.getUserFromRequestAttr(nativeWebRequest);
        BusinessExceptionEnum.INVAILD_TOKEN.assertNotNull(userModel);
        return userModel;
    }
}
