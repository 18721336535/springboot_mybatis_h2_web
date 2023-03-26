package com.zbq.controller;

import com.zbq.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;



@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public List<User> findAll(){
        System.out.println("test");
        return null;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public User findById(Integer id){
        return null;
    }

    /**
     * 更新
     * @param user
     */
    @RequestMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        System.out.println(user);
    }
}
