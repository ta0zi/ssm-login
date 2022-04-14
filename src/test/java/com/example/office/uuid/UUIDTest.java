/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: UUIDTest
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/11 13:02
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


package com.example.office.uuid;


import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/11

 * @since 1.0.0

 */

public class UUIDTest {
    public static void main(String[] args){
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
    }


}
