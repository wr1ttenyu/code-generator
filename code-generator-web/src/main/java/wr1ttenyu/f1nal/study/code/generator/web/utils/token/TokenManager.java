package wr1ttenyu.f1nal.study.code.generator.web.utils.token;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.TokenModel;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums.BusinessExceptionEnum;
import wr1ttenyu.f1nal.study.code.generator.util.common.constant.enums.CommonResponseEnum;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TokenManager {

    private static final Logger logger = LoggerFactory.getLogger(TokenManager.class);

    private static String CHARSET_NAME = "UTF-8";
    private static final String TOKEN = "Token";
    private static final String USER = "user";

    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * 生成token
     * @param user
     * @return
     */
    public static TokenModel makeToken(UserModel user) {
        TokenModel TokenModel = new TokenModel();
        try {
            TokenModel.setToken(encoder.encodeToString(JSONObject.toJSONString(user).getBytes("UTF-8")));
            TokenModel.setUser(user);
        } catch (UnsupportedEncodingException e) {
            logger.error("用户Token生成失败，errMsg:{}", e);
            CommonResponseEnum.SERVER_BUSY.assertFail();
        }
        return TokenModel;
    }

    public static TokenModel decoderToken(String token) {
        TokenModel TokenModel = new TokenModel();
        try {
            String userInfo = new String(decoder.decode(token.getBytes("UTF-8")));
            UserModel userModel = JSONObject.parseObject(userInfo, UserModel.class);
            TokenModel.setToken(token);
            TokenModel.setUser(userModel);
        } catch (Exception e) {
            logger.error("用户Token解析失败，errMsg:{}", e);
            BusinessExceptionEnum.INVAILD_TOKEN.assertFail();
        }
        return TokenModel;
    }

    public static String getTokenFromHead(HttpServletRequest httpServletRequest) {
       return httpServletRequest.getHeader(TOKEN);
    }

    public static void saveUserToRequestAttr(HttpServletRequest httpServletRequest, TokenModel tokenModel) {
        httpServletRequest.setAttribute(USER, tokenModel.getUser());
    }

    public static UserModel getUserFromRequestAttr(NativeWebRequest nativeWebRequest) {
        return (UserModel) nativeWebRequest.getAttribute(USER, RequestAttributes.SCOPE_REQUEST);
    }
}
