package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(MybatisController.class);
    @RequestMapping("/query")
    @ResponseBody
    public List<User> queryUserList(){
        List<User> users = userMapper.queryUserList();
        logger.info("日志打印记录成功");
        logger.info(JSON.toJSONString(users));
        return users;
    }

}
