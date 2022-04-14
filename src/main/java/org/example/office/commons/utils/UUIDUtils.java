/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: UUIDUtils
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/11 13:16
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


package org.example.office.commons.utils;


import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/11

 * @since 1.0.0

 */

/**
 * 获取uuid的值
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
