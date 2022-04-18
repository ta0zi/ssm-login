package org.example.office.workbench.service;

import org.example.office.workbench.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    int saveCreateStudent(Student student);

    List<Student> queryStudentByConditionForPage(Map<String,Object> map);

    int queryCountOfStudentByCondition(Map<String,Object> map);
}
