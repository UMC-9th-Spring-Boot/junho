package com.example.umc9th.global.resolver;

import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.code.status.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String page = webRequest.getParameter("page");
        int pageInt = (page == null) ? 1 : Integer.parseInt(page);

        if (pageInt <= 0) {
            throw new GeneralException(GeneralErrorCode.PAGE_NUMBER_UNDER_ZERO);
        }
        return pageInt-1; // 프론트 1-based -> 백 0-based
    }
}
