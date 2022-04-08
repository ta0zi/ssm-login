/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: IndexController
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/3 14:09
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


package org.example.office.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/3

 * @since 1.0.0

 */
@Controller
public class IndexController {
@RequestMapping("/")
    public String index(){
    return "index";
}

}
