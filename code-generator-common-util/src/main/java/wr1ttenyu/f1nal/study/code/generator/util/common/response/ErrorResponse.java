package wr1ttenyu.f1nal.study.code.generator.util.common.response;

import wr1ttenyu.f1nal.study.code.generator.util.common.exception.IResponseEnum;

/**
 * <p>错误返回结果</p>
 */
public class ErrorResponse extends BaseResponse {

    public static ErrorResponse errorResponse(IResponseEnum responseEnum) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(responseEnum.getCode());
        response.setMessage(responseEnum.getMessage());
        return response;
    }

    public static ErrorResponse errorResponse(IResponseEnum responseEnum, String errMsg) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(responseEnum.getCode());
        response.setMessage(errMsg);
        return response;
    }
}
