package com.bxp.web.springweb.dao;

import com.bxp.web.springweb.train.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;


/**
 * @Author: bbp(xiuping bao) on 2018/5/26.
 * @Date: 2018/5/26 17:21
 */
@Mapper
public interface UserDao {
    @Select("SELECT * FROM user_info where ID=#{id}")
    @ResultType(value = UserInfo.class)
    UserInfo getUserInfo(@Param("id") String id);
}
