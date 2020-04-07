package com.example.springBootDemo.interceptor;

/**
 * 拦截器
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.springBootDemo.filter.ParameterFilter;

@Component
public class UriInterceptor implements HandlerInterceptor {

	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("pre Interceptor");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView == null || modelAndView.getViewName().startsWith("redirect:")) {
			return;
		}
		LOGGER.debug("post Interceptor");
		String uri = request.getServletPath();
		//	LOGGER.debug("-----------" + uri);
		//引用了org.apache.commons的commons-lang3，下面两个作用一致
		if (StringUtils.isNotBlank(uri) /*uri != null || uri != ""*/) {
			if (uri.startsWith("/")) {
				uri.substring(1);
			}
		}
		String template = (String) modelAndView.getModelMap().get("template");
		if (StringUtils.isBlank(template) /*template == null || template == ""*/) {
			modelAndView.getModelMap().addAttribute("template", uri.toLowerCase());
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.debug("after Interceptor");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
