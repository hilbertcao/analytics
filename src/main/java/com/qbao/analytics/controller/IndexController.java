package com.qbao.analytics.controller;

import com.qbao.analytics.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.UUID;

@Controller public class IndexController {

    private final static String ROOT_DOMAIN = ".qbao.com";
    @RequestMapping(path = {"/index.html","/"})
    public String greeting(String name,
            Model model) {
        return "index";
    }

    @RequestMapping(value = "/1.gif", method = RequestMethod.GET)
    public String collect(HttpServletRequest servletRequest, HttpServletResponse response)
            throws Exception {

        System.out.println("收集消息");
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
            cookie.setDomain(ROOT_DOMAIN);
            cookie.setPath("/");
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
        }
        System.out.println("clientId:"+clientId);
        return "success";
    }

    @RequestMapping("/login.do")
    public String form(@RequestParam( "userName" ) String userName,HttpServletResponse response) {

    Cookie cookie = new Cookie("uid", String.valueOf(UserRepository.getUserId(userName)));
        cookie.setDomain(ROOT_DOMAIN);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "detail";
    }

    @RequestMapping("/logout.do")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("uid","0");
        cookie.setDomain(ROOT_DOMAIN);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "index";
    }

    @RequestMapping("/order.do")
    public String order(HttpServletRequest servletRequest) {

        return "success";
    }

    @RequestMapping("/qiandao.do")
    public String qiandao(HttpServletRequest servletRequest) {

        return "success";
    }

}
