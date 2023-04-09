package com.zbq;

import com.zbq.domain.User;
import com.zbq.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zbq
 */
public class UserTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("src/src/main/resources/SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void queryUserList(){
//        List<User> users = userMapper.queryUserList();
        User userParm = new User();
        userParm.setOffset(0);
        userParm.setPageSize(2);
        List<User> userList = userMapper.queryUsers(userParm);
        for(User user : userList){
            System.out.println("-----每个用户的信息------");
//            System.out.println(user.getUserName());
        }
    }

    /*调用Arrays.asList()生产的List的add、remove方法时报异常，
      这是由Arrays.asList() 返回的是Arrays的内部类ArrayList， 而不是java.util.ArrayList。
      Arrays的内部类ArrayList和java.util.ArrayList都是继承AbstractList，remove、add等方法AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。
      java.util.ArrayList重新了这些方法而Arrays的内部类ArrayList没有重新，所以会抛出异常。*/
    @Test
    public void lstOperationTest(){
        User ua = new User();
//        ua.setUserName("123");
        ua.setUserIdNo(null);
        List<User> lista = new ArrayList<>(); lista.add(ua);
        //List<User> lista = Arrays.asList(ua);

        User ub = new User();
//        ub.setUserName("1236");
        ub.setUserIdNo("235");
        List<User> listb = new ArrayList<>(); listb.add(ub);
        //List<User> listb = Arrays.asList(ub);


        lista.removeAll(listb);
        lista.addAll(listb);


    }



}
