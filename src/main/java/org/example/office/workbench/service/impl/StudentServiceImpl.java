/**
 * Copyright (C), 2015-2022, XXX有限公司
 * <p>
 * FileName: StudentServiceImpl
 * <p>
 * Author:   taozi
 * <p>
 * Date:     2022/4/9 15:47
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


package org.example.office.workbench.service.impl;


import org.example.office.workbench.domain.Student;
import org.example.office.workbench.mapper.StudentMapper;
import org.example.office.workbench.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author taozi

 * @create 2022/4/9

 * @since 1.0.0

 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int saveCreatStudent(Student student) {
        return studentMapper.insertStudent(student );
    }
}
