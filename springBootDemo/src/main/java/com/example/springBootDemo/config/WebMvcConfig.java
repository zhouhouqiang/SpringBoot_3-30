package com.example.springBootDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.springBootDemo.filter.ParameterFilter;
import com.example.springBootDemo.interceptor.UriInterceptor;

@Configuration
@AutoConfigureAfter({ WebMvcAutoConfiguration.class }) //配置顺序，覆盖掉
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private UriInterceptor uriInterceptor;

	//过滤器
	@Bean
	public FilterRegistrationBean<ParameterFilter> filterRegister() {
		FilterRegistrationBean<ParameterFilter> bean = new FilterRegistrationBean<ParameterFilter>();
		bean.setFilter(new ParameterFilter());
		return bean;
	}

	//拦截器 需要implements WebMvcConfigurer
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册并添加路径匹配
		registry.addInterceptor(uriInterceptor).addPathPatterns("/**");
		//WebMvcConfigurer.super.addInterceptors(registry);
	}

}
