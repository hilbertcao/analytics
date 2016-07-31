package com.qbao.analytics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.UUID;

@Controller public class IndexController {

    @RequestMapping("/index.html")
    public String greeting(String name,
            Model model) {
        model.addAttribute("name", name + "ed10");
        return "index";
    }

    @RequestMapping(value = "/1.gif", method = RequestMethod.GET)
    public String collect(HttpServletRequest servletRequest, HttpServletResponse response)
            throws Exception {

        System.out.println("收集消息");
        for (String key:servletRequest.getParameterMap().keySet()
             ) {
            System.out.println(key+":"+servletRequest.getParameterMap().get(key)[0]);
        }
        System.out.println("ip:"+servletRequest.getRemoteAddr());
        String clientId=null;
        if(servletRequest.getCookies() != null){
            for(Cookie cookie:servletRequest.getCookies()){
                if("clientId".equals(cookie.getName())){

                    clientId = cookie.getValue();
                }
            }
        }

        if( (clientId== null) || "".equals(clientId)) {
            clientId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("clientId", clientId);
            cookie.setDomain(".ab.com");
            cookie.setPath("/");
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
        }
        System.out.println("clientId:"+clientId);
        return "success";
    }

    @RequestMapping("/login.do")
    public String form(HttpServletRequest servletRequest) {

        System.out.println("登录表单提交数据");
        for (String key:servletRequest.getParameterMap().keySet()
                ) {
            System.out.println(key+":"+servletRequest.getParameterMap().get(key)[0]);
        }
        return "success";
    }

    @RequestMapping("/ajax.do")
    public String ajax(HttpServletRequest servletRequest) {

        System.out.println("ajax提交数据");
        try {
            String records = URLDecoder.decode(servletRequest.getCookies()[1].getValue(),"UTF-8");
            System.out.println(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String key:servletRequest.getParameterMap().keySet()
                ) {
            System.out.println(key+":"+servletRequest.getParameterMap().get(key)[0]);
        }
        return "success";
    }
}
