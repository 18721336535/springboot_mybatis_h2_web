package com.zbq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneTest {
    public static void main(String args[]) throws ParseException {
        SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(now);
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(now2);
        LocalDateTime now1 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now1);


        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));//Thu Jan 01 00:00:00 CST 1970
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr0 = "2021-03-13 18:50:56.356";
//        Date date1 = simpleDateFormat.parse(dateStr0);
        System.out.println(new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));//Thu Jan 01 00:00:00 PST 1970

//        String dateStr0 = "1970-01-01 00:00:00";
        String dateStr = "2021-03-13 18:50:56.356";
        Date date = simpleDateFormat.parse(dateStr);
        System.out.println(date);
    }
}
