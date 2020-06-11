package wr1ttenyu.f1nal.study.code.generator.util.common.response;

import lombok.Data;
import wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums.CommonResponseEnum;

/**
 * <p>通用返回结果</p>
 */
@Data
public class CommonResponse<T> extends BaseResponse {
    /**
     * 数据列表
     */
    protected T data;

    public static CommonResponse successResponse() {
        CommonResponse response = new CommonResponse();
        response.setCode(CommonResponseEnum.SUCCESS.getCode());
        response.setMessage(CommonResponseEnum.SUCCESS.getMessage());
        return response;
    }

    public static <T extends Object> CommonResponse<T> successResponse(T data) {
        CommonResponse response = new CommonResponse();
        response.setCode(CommonResponseEnum.SUCCESS.getCode());
        response.setMessage(CommonResponseEnum.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }
}
