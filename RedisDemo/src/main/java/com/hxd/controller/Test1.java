package com.hxd.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxd.entry.User;

@Controller
public class Test1 {
	//用来查看配置SpringMvc是否能成功运行
	@RequestMapping("/viewPage")
	public String test(){
		return "test";
	}
	
	// 登录
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request,String userName,String password){
		//JSONObject jsonObject=new JSONObject();
		Map<String, Object> map=new HashMap<String, Object>();
		if ("123".equals(userName)&&"123".equals(password)) {
			User user=new User();
			user.setUseName(userName);
			user.setPassword(password);
			//redis session中无法存贮对象  将其是用fastJson转换
			request.getSession().setAttribute("user1",JSON.toJSONString(user));
			map.put("msg", "登录成功");
		}else {
			map.put("msg", "登录成功");
		}
		return map;
	}
	// 验证 能否进入Url    (无session 会报 空指针异常)
	@RequestMapping("/user")
	public ModelAndView user(HttpServletRequest request){
	String userString= request.getSession().getAttribute("user1").toString();
	    User user=JSON.parseObject(userString, User.class);
	    ModelAndView mv=new ModelAndView();
		mv.setViewName("sucess");
		mv.addObject("user", user);
		return mv;
	}
	
	// 使session失效
	@RequestMapping("/session")
	public void  session(HttpServletRequest request){
			request.getSession().invalidate();
	}
	
}
