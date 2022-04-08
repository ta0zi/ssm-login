/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: MainController
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/8 13:58
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


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/8

 * @since 1.0.0

 */
@Controller
public class MainController {
    @RequestMapping("/workbench/main/index.do")
    public String index(){
        //跳转到main/index.jsp
        return "workbench/main/index";
    }

}
