package javax_mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailGenerater {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.126.com");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("----w15719290065@126.com", "JTRNDUKEVKRUQNSH");
            }
        });

//        return new PasswordAuthentication("zenbinqin135@163.com","20092014zbq");

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addFrom(new Address[]{new InternetAddress("----w15719290065@126.com")});
            msg.setRecipients(Message.RecipientType.TO, "1186014040@qq.com");
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            msg.setText("Hello, world!\n");
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
}
