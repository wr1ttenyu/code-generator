package wr1ttenyu.f1nal.study.code.generator.web.utils.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.TokenModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenHandleInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenHandleInterceptor.class);

    private static final Map<Class, Object> HAS_TOKEN_CLASS_CACHE = new ConcurrentHashMap<>();
    private static final Map<HandlerMethod, Object> HAS_TOKEN_METHOD_CACHE = new ConcurrentHashMap<>();


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
            o) throws Exception {
        // 如果不是映射到方法，直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = ((HandlerMethod) o);
        // 类上有注解  全部方法过滤token
        Class<?> beanType = handlerMethod.getBeanType();
        TokenValid tokenValid = beanType.getAnnotation(TokenValid.class);

        if (tokenValid == null) {
            Method method = handlerMethod.getMethod();
            // 获取方法上的tokenvalid注解
            tokenValid = method.getAnnotation(TokenValid.class);
        }

        if (tokenValid != null) {
            TokenModel tokenModel = TokenManager.decoderToken(TokenManager.getTokenFromHead(httpServletRequest));
            //将user 添加到 request中，以便后续操作获取user
            TokenManager.saveUserToRequestAttr(httpServletRequest, tokenModel);
        }

        return true;
    }
}
