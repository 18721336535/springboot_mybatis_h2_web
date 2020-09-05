package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.ResultInfo;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zengbingqing cfg test x1-1 add x1-2 add x1-3 add x1-4
 * @Author: zengbingqing cfg test x2-1
 * @Author: zengbingqing cfg test x1-1
 * @Author: zengbingqing cfg test x1-1 add x1-2
 * @Author: zengbingqing cfg test x1-1 add x1-2 add x1-3
 * @Author: zengbingqing zengbingqing add dev+ release_1 b1
 * @Description:
**/
@Controller
public class MybatisController {

    @Autowired
    private UserMapper userMapper;
    //id t2 vx1 
    //1, 2 3
    @RequestMapping("/user/login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据 3 4
        Map<String, String[]> map = request.getParameterMap();
        Map<String,Object> result = new HashMap<>();
        result.put("flag",false);
        System.out.println(JSON.toJSONString(map));
        System.out.println(JSON.toJSONString(result));
        request.getSession().setAttribute("user","1234567890");//登录成功标记
        response.setHeader("Access-Control-Allow-Origin", "*");
		//x t2 t3 t3-c
        return  result;
//        //2.封装User对象
//        User user = new User();
//        try {
//            BeanUtils.populate(user,map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        //3.调用Service查询
//        // UserService service = new UserServiceImpl();
//        User u  = new User();//service.login(user);
//
//        ResultInfo info = new ResultInfo();
//
//        //4.判断用户对象是否为null
////        if(u == null){
////            //用户名密码或错误
////            info.setFlag(false);
////            info.setErrorMsg("用户名密码或错误");
////        }
//        //5.判断用户是否激活
////        if(u != null && !"Y".equals(u.getStatus())){
////            //用户尚未激活
////            info.setFlag(false);
////            info.setErrorMsg("您尚未激活，请激活");
////        }
//        //6.判断登录成功
////        if(u != null && "Y".equals(u.getStatus())){
//            request.getSession().setAttribute("user",u);//登录成功标记
//
//            //登录成功
//            info.setFlag(true);
////        }
//
//        //响应数据
//        ObjectMapper mapper = new ObjectMapper();
//
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(),info);
    }


    @RequestMapping("/user/loginnext")
    @ResponseBody
    public Map<String,Object> loginnext(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        Map<String, Object> result = new HashMap<>();
        result.put("flag", false);
        System.out.println(JSON.toJSONString(map));
        System.out.println(JSON.toJSONString(request.getCookies()));
        //request.getSession().setAttribute("user", "1234567890");//登录成功标记
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result;
    }

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
