package com.example.springBootDemo.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springBootDemo.modules.test.vo.ConfigBean;

@Controller
@RequestMapping("/test")
public class TestController {

	private final static Logger Logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/demoInfo")
	@ResponseBody
	public String demoDesc() {
		return "Hello World!";
	}

	@Value("${server.port}")
	private int port;
	@Value("${com.zhq.name}")
	private String name;
	@Value("${com.zhq.age}")
	private int age;
	@Value("${com.zhq.desc}")
	private String desc;
	@Value("${com.zhq.random}")
	private String random;

	@Autowired
	private ConfigBean configBean;

	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap) {
		modelMap.addAttribute("template", "test/index");
		return "index";
	}

	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		Logger.trace("This is trace log.");
		Logger.debug("This is debug log.");
		Logger.info("This is info log.");
		Logger.warn("This is warn log.");
		Logger.error("This is error log.");
		return "This is log test.";
	}

	@RequestMapping("/config")
	@ResponseBody
	public String configTest() {
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("--").append(name).append("--").append(age).append("--").append(desc).append("--")
				.append(random).append("</br>");
		sb.append(configBean.getName()).append("--").append(configBean.getAge()).append("--")
				.append(configBean.getDesc()).append("--").append(configBean.getRandom()).append("--");

		return sb.toString();
	}

}
