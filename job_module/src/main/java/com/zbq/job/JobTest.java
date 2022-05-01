package com.zbq.job;

import com.zbq.domain.User;
import com.zbq.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobTest {
//    private static final Logger logger = LoggerFactory.getLogger(JobTest.class);
private static final Logger logger = LoggerFactory.getLogger(JobTest.class);
//    private static Logger logger = LogManager.getLogger(JobTest.class);
    private int i;
    @Autowired
    private UserMapper userMapper;
    @Scheduled(cron = "*/15 * * * * ?")
    public void execute() throws InterruptedException {
        logger.info("execute-listadd");
//        List<User> users= userMapper.queryUserList();
//        logger.info("execute-users{}",users.size());
//
//        List<String> bzlist = new ArrayList<String>();
//        while(true) {
//            Thread.sleep(10);
//            bzlist.add("oomTest");
//        }
    }


    @Scheduled(cron = "*/40 * * * * ?")
    public void execute1() throws InterruptedException {
        logger.info("execute1-users uery");
//        List<String> bzlist = new ArrayList<String>();
//        List<User> users= userMapper.queryUserList();
//        logger.info("thread id:{},FixedPrintTask execute times:"+Thread.currentThread().getId());
    }

}
