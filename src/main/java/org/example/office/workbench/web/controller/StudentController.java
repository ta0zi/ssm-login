/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: StudentController
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/9 15:55
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


package org.example.office.workbench.web.controller;


import org.apache.poi.ss.formula.functions.Replace;
import org.example.office.commons.contants.Contants;
import org.example.office.commons.utils.UUIDUtils;
import org.example.office.settings.domain.User;
import org.example.office.settings.service.UserService;
import org.example.office.workbench.domain.Student;
import org.example.office.workbench.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/9

 * @since 1.0.0

 */
@Controller
public class StudentController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @RequestMapping("/workbench/student/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询所有的用户
        List<User> userList=userService.queryAllUsers();
        //把数据保存到request中
        request.setAttribute("userList",userList);
        //请求转发到市场活动的主页面
        return "workbench/student/index";
    }
    @RequestMapping("/workbench/student/saveCreateStudent.do")
    public @ResponseBody Object saveCreateStudent(Student student){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        student.setId(UUIDUtils.getUUID());
        student.getCreateBy(user.getId());
        //调用service曾方法，保存创建的市场活动
        try {
            int ret=studentService.saveCreatStudent(student);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
