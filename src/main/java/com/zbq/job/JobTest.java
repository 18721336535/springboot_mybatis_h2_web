package com.zbq.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobTest {
//    private static final Logger logger = LoggerFactory.getLogger(JobTest.class);
private static final Logger logger = LoggerFactory.getLogger(JobTest.class);
//    private static Logger logger = LogManager.getLogger(JobTest.class);
    private int i;

    @Scheduled(cron = "*/15 * * * * ?")
    public void execute() {
        logger.info("thread id:{},FixedPrintTask execute times:"+Thread.currentThread().getId());
    }

}
