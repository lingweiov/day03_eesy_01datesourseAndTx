package com.ithaima.dao;/*
@outhor shkstart
@date 2019/12/11-11:22
用户的持久层借口
*/

import com.ithaima.domain.QueryVo;
import com.ithaima.domain.User;
import java.util.List;

public interface IUserDao {
//@Select("select * from user")
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();
    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);
}
