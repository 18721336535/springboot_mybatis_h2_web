package com.zbq;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest {

    private static String host;
    private static String port;
    private static String nick;
    private static String username;
    private static String password;
    private static String from;
    private static List<String> toList;

    static {
        init();
    }

    private static boolean isEmpty() {
        if (nick != null && host != null && port != null && username != null && password != null && from != null && toList != null) {
            return false;
        }
        return true;
    }

    private static void init() {
        toList = new ArrayList<String>();
//        InputStream inStream = EmailTest.class.getResourceAsStream("D:\\myprojects\\springboot_batis_H2\\springboot_mybatis_H2_Web\\src\\test\\email.properties\\email.properties");
        try {
//            Properties prop = new Properties();
//            prop.load(inStream);
//            host = prop.getProperty("mail.host");
//            port = prop.getProperty("mail.port");
//            nick = prop.getProperty("mail.nick");
//            username = prop.getProperty("mail.username");
//            password = prop.getProperty("mail.password");
//            from = prop.getProperty("mail.from");
//            String toListStr = prop.getProperty("mail.toList");
            host = "smtp.office365.com";
            port = "587";
            nick = "admin";
            username = "xxx@outlook.com";
            password = "xxxx";
            from = "zengbingqing135@outlook.com";
            String toListStr = "xxx@outlook.com;xxx@qq.com";
            String[] split = toListStr.split(";");
            for (String string : split) {
                toList.add(string);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (inStream != null) {
//                try {
//                    inStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    public static boolean sendEmail(String title,String content) {

        if(isEmpty()) {
            init() ;
        }

        Properties properties = System.getProperties();

        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");// outlook邮箱需要加上

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getDefaultInstance(properties, authenticator);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(nick + " <"+from +">"));

            /**
             *  设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
             * MimeMessage.RecipientType.TO:发送
             * MimeMessage.RecipientType.CC：抄送
             * MimeMessage.RecipientType.BCC：密送
             */
            for (String to : toList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }

            message.setSubject(title, "UTF-8");
            message.setContent(content, "text/html;charset=UTF-8");

            Transport.send(message);

            System.out.println("Sent message successfully.... title："+title);
            return true ;

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        String content = "<a href='http://www.baidu.com'>测试地址</a>" ;
        EmailTest.sendEmail("测试内容", content) ;

    }

}
