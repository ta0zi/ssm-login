package org.example.office.settings.service;

import org.example.office.settings.domain.User;

import java.util.Map;

public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);
}
