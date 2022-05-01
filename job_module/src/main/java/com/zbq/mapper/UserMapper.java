package com.zbq.mapper;

import com.zbq.domain.User;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.Entity;
import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> queryUserList();
    public List<User> queryUsers(User user);

}
