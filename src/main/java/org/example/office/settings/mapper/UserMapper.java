package org.example.office.settings.mapper;

import org.example.office.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zh
     *
     * @mbggenerated Wed Apr 06 20:00:41 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zh
     *
     * @mbggenerated Wed Apr 06 20:00:41 CST 2022
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zh
     *
     * @mbggenerated Wed Apr 06 20:00:41 CST 2022
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zh
     *
     * @mbggenerated Wed Apr 06 20:00:41 CST 2022
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zh
     *
     * @mbggenerated Wed Apr 06 20:00:41 CST 2022
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zh
     *
     * @mbggenerated Wed Apr 06 20:00:41 CST 2022
     */
    int updateByPrimaryKey(User record);

    User selectUserByLoginActAndPwd(Map<String, Object> map);

    /**
     * 查询所有的用户
     * @return
     */
    List<User> selectAllUsers();
}