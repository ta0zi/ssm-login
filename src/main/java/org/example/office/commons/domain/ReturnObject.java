/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: ReturnObject
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/5 3:20
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


package org.example.office.commons.domain;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/5

 * @since 1.0.0

 */

public class ReturnObject {
private  String code;//处理成功获取标志
private String message;//提示信息

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
