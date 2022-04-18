/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: UserController
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/3 14:43
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


package org.example.office.settings.web.controller;


import org.example.office.commons.contants.Contants;
import org.example.office.commons.domain.ReturnObject;
import org.example.office.settings.domain.User;
import org.example.office.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/3

 * @since 1.0.0

 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
    return "settings/qx/user/login";
    }
    @RequestMapping("/settings/qx/user/login.do")

    public @ResponseBody Object Login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        //调用service层方法，查询用户
        User user = userService.queryUserByLoginActAndPwd(map);
        //根据查询结果，生成响应信息
        ReturnObject returnObject = new ReturnObject();
        if (user == null) {
            //登录失败,用户名或者密码错误
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FALL);
            returnObject.setMessage("用户名或者密码错误");
        } else {
            //登录成功
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

            //把user保存到session中
            session.setAttribute("sessionUser", user);

        }

        //如果需要记住密码，则往外写cookie
        if ("true".equals(isRemPwd)) {
            Cookie c1 = new Cookie("loginAct", user.getLoginAct());
            c1.setMaxAge(10 * 24 * 60 * 60);
            response.addCookie(c1);
            Cookie c2 = new Cookie("loginPwd", user.getLoginPwd());
            c2.setMaxAge(10 * 24 * 60 * 60);
            response.addCookie(c2);
        } else {
            //把没有过期cookie删除
            Cookie c1 = new Cookie("loginAct", "1");
            c1.setMaxAge(0);
            response.addCookie(c1);
            Cookie c2 = new Cookie("loginPwd", "1");
            c2.setMaxAge(0);
            response.addCookie(c2);
        }


        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空cookie
        Cookie c1=new Cookie("loginAct","1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2=new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        session.invalidate();
        //跳转到首页
        return "redirect:/";
    }
}
