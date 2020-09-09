package com.cxl.security.handler;

import com.cxl.security.code.CodeMessage;
import com.cxl.security.result.ResultModel;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cxl
 * @description 失败处理
 * @date 2020/09/09 0009
 */
@Component("myAuthenctiationFailureHandler")
@Slf4j
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("登录失败{}");
        ResultModel resultModel = new ResultModel(CodeMessage.FAIL.getCode(),CodeMessage.FAIL.getMessage(),exception.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(resultModel.toString());

    }
}
