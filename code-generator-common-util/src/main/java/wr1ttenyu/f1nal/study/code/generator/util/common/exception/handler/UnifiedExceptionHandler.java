package wr1ttenyu.f1nal.study.code.generator.util.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums.ArgumentExceptionEnum;
import wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums.CommonResponseEnum;
import wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums.ServletResponseEnum;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.BaseException;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.BusinessException;
import wr1ttenyu.f1nal.study.code.generator.util.common.exception.IResponseEnum;
import wr1ttenyu.f1nal.study.code.generator.util.common.i18n.UnifiedMessageSource;
import wr1ttenyu.f1nal.study.code.generator.util.common.response.ErrorResponse;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * <p>全局异常处理器</p>
 */
@Slf4j
@Component
@ControllerAdvice
@ConditionalOnWebApplication
public class UnifiedExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(UnifiedExceptionHandler.class);

    /**
     * 生产环境
     */
    private final static String ENV_PROD = "prod";

    @Autowired
    private UnifiedMessageSource unifiedMessageSource;

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active:}")
    private String profile;

    @PostConstruct
    public void test() {
        if(StringUtils.isEmpty(profile)) logger.info("当前 spring profile 配置未指定，默认为空，非生产环境");
        else logger.info("当前 spring profile 配置为：" + profile);
    }

    /**
     * 获取国际化消息
     *
     * @param e 异常
     * @return
     */
    public String getMessage(BaseException e) {
        String code = "response." + e.getResponseEnum().toString();
        String message = unifiedMessageSource.getMessage(code, e.getArgs());

        if (message == null || message.isEmpty()) {
            return e.getMessage();
        }

        return message;
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorResponse handleBusinessException(BaseException e) {
        log.error(e.getMessage(), e);
        return ErrorResponse.errorResponse(e.getResponseEnum(), getMessage(e));
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ErrorResponse handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        return ErrorResponse.errorResponse(e.getResponseEnum(), getMessage(e));
    }

    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({
        NoHandlerFoundException.class,
        HttpRequestMethodNotSupportedException.class,
        HttpMediaTypeNotSupportedException.class,
        HttpMediaTypeNotAcceptableException.class,
        MissingPathVariableException.class,
        MissingServletRequestParameterException.class,
        TypeMismatchException.class,
        HttpMessageNotReadableException.class,
        HttpMessageNotWritableException.class,
        ServletRequestBindingException.class,
        ConversionNotSupportedException.class,
        MissingServletRequestPartException.class,
        AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public ErrorResponse handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        IResponseEnum serverError = CommonResponseEnum.SERVER_ERROR;
        try {
            ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            serverError = servletExceptionEnum;
        } catch (IllegalArgumentException e1) {
            log.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletResponseEnum.class.getName());
        }

        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
            return ErrorResponse.errorResponse(CommonResponseEnum.SERVER_ERROR);
        }

        return ErrorResponse.errorResponse(serverError, e.getMessage());
    }

    /**
     * 参数校验异常
     * ConstraintViolation 原生异常
     * @Validated 标记整个 Controller 时，方法入参非自定义对象校验失败，抛出的异常
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ErrorResponse handleException(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        log.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        constraintViolations.stream().forEach((va) -> sb.append("; ").append(va.getMessageTemplate()));
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            sb.append("; ").append(constraintViolation.getMessageTemplate());
        }
        return ErrorResponse.errorResponse(ArgumentExceptionEnum.VALID_ERROR, sb.toString().substring(2));
    }


    /**
     * 参数绑定异常
     * @Validated 抛出的 spring 自实现
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ErrorResponse handleBindException(BindException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     * @Valid jsr303
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleValidException(MethodArgumentNotValidException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        if (ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
            return ErrorResponse.errorResponse(CommonResponseEnum.SERVER_ERROR);
        }

        return ErrorResponse.errorResponse(CommonResponseEnum.SERVER_ERROR, e.getMessage());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ErrorResponse wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }

        return ErrorResponse.errorResponse(ArgumentExceptionEnum.VALID_ERROR, msg.substring(2));
    }

}
