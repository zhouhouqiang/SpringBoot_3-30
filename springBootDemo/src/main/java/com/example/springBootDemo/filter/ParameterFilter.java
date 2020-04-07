package com.example.springBootDemo.filter;

/**
 * 过滤器
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "parameterFilter", urlPatterns = "/**")
public class ParameterFilter implements Filter {

	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 被锁住，没有权限操作，需使用HttpServletRequestWrapper
		//	Map<String, String[]> map = httpRequest.getParameterMap();
		//	map.put("testKey", new String[] { "****" });

		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
			//public String demoDesc(HttpServletRequest request, @RequestParam String key)
			//对于第一种查询参数获取 HttpServletRequest request
			@Override
			public String getParameter(String name) {
				String value = httpRequest.getParameter(name);
				if (value != null || value != "") {
					return value.replace("fuck", "**");
				}
				return super.getParameter(name);
			}

			//对于第二种查询参数获取（注解方式） @RequestParam String key
			@Override
			public String[] getParameterValues(String name) {
				String[] values = httpRequest.getParameterValues(name);
				if (values != null && values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						values[i] = values[i].replace("fuck", "***");
					}
					return values;
				}
				return super.getParameterValues(name);
			}

		};

		chain.doFilter(wrapper, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("init parameter filter");
		Filter.super.init(filterConfig);
	}

	@Override
	public void destroy() {
		LOGGER.debug("destroy parameter filter");
		Filter.super.destroy();
	}

}
