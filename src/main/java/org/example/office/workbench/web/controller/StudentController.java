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


import org.example.office.commons.contants.Contants;
import org.example.office.commons.domain.ReturnObject;
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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public @ResponseBody Object saveCreateStudent(Student student, HttpSession session){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        student.setId(UUIDUtils.getUUID());
        student.setCreateBy(user.getId());

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的市场活动
            int ret = studentService.saveCreateStudent(student);

            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FALL);
                returnObject.setMessage("系统忙,请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FALL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/student/queryStudentByConditionForPage.do")
    public @ResponseBody Object queryStudentByConditionForPage(String name,String owner,String age,String email,
    int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("age",age);
        map.put("email",email);
        map.put("begonNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Student> studentList=studentService.queryStudentByConditionForPage(map);
        int totalRows=studentService.queryCountOfStudentByCondition(map);
        //根据查询结果，生成响应信息
        Map<String,Object> retMap=new HashMap<>();
        retMap.put("studentList",studentList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }
}
