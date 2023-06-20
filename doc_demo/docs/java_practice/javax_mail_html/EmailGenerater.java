package javax_mail_html;

import generateExcel.OTCProduct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.NumberFormat;
import java.util.*;

public class EmailGenerater {
    public static void main(String[] args){
        String contennt = generateEmailHtmlBody();
        //create session for send email
        Session session = getSession();
        try {
            MimeMessage msg = new MimeMessage(session);
//            msg.addFrom(new Address[]{new InternetAddress("----w15719290065@126.com")});
//            msg.setRecipients(Message.RecipientType.TO, "1186014040@qq.com");
//            msg.setSubject("JavaMail hello world example");
//            msg.setSentDate(new Date());
            msg=new EmailGenerater().createEmailBody(msg);
            //generate excel attachments
            msg=new EmailGenerater().createEmailAttachment(msg);
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }

    public MimeMessage createEmailBody(MimeMessage msg) throws MessagingException {
        String mailContent = generateEmailHtmlBody();

        MimeMessageHelper helper  = new MimeMessageHelper(msg,true);

        helper.setFrom("email address");
        helper.setSubject("subject");
        helper.setText(mailContent,true);
        helper.setTo(StringUtils.split("123@136.com;asd@qq.com",";"));

        helper.addAttachment("fileName",new File("filepath"));

        return msg;

    }

    protected static String generateEmailHtmlBody() {
        List<OTCProduct> products = new ArrayList<>();
        products.add(new OTCProduct("c1","ca1",100,1000));
        products.add(new OTCProduct("c1","ca2",200,2000));
        products.add(new OTCProduct("c2","cb1",300,3000));
        products.add(new OTCProduct("c2","cb2",400,4000));


        HTMLMailContentWriter contentWriter = new HTMLMailContentWriter("");

        HTMLWriter htmlWriter = new HTMLWriter("TableName");
        htmlWriter.createRow();
        htmlWriter.writeHeader(Arrays.asList("category1","category2","originPrice","salePrice"));
        Map<String,ExcelIndexVO> mergeIndexMap = new HashMap<>();
        generateMergeMap(products, mergeIndexMap);
        Map<String,String> columnAttr;
        for (OTCProduct product:products) {
            columnAttr = new HashMap<>();
            if (product.isTotalRow()) htmlWriter.createTotalRow();
            if(product.isHighLightRow()) columnAttr.put("style","background-color: rgb(250,0,0);");
            htmlWriter.createRow();

            if(mergeIndexMap.containsKey(product.getCategory1())){
                ExcelIndexVO excelIndexVO = mergeIndexMap.get(product.getCategory1());
                htmlWriter.writeDataWithRowSpan(product.getCategory1(),excelIndexVO.getLastRow()-excelIndexVO.getFirstRow()+1);
            }
            htmlWriter.writeData(product.getCategory2(),columnAttr);
            columnAttr.put("align", "right");
            htmlWriter.writeData(NumberFormat.getInstance(Locale.US).format(product.getOriginPrice()),columnAttr);
            htmlWriter.writeData(NumberFormat.getInstance(Locale.US).format(product.getSalePrice()),columnAttr);
            mergeIndexMap.remove(product.getCategory1());
        }
        contentWriter.addGeneratedReportAsHTML(htmlWriter);
        String mailContent =contentWriter.generateMailContent();
        return mailContent;
    }

    private static void generateMergeMap(List<OTCProduct> products, Map<String, ExcelIndexVO> mergeIndexMap) {
        int firstRowIndex;
        int lastRowIndex = 0;
        for (OTCProduct product: products){
            if(!mergeIndexMap.containsKey(product.getCategory1())){
                firstRowIndex = ++lastRowIndex;
                ExcelIndexVO excelIndexVO = new ExcelIndexVO();
                excelIndexVO.setFirstRow(firstRowIndex);
                excelIndexVO.setLastRow(lastRowIndex);
                mergeIndexMap.put(product.getCategory1(),excelIndexVO);
                continue;
            }
            mergeIndexMap.get(product.getCategory1()).setLastRow(++lastRowIndex);
        }
    }

    private MimeMessage createEmailAttachment(MimeMessage msg){
        return msg;

    }



    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.126.com");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //provide sftp server connection info: uemail and validation code
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //        return new PasswordAuthentication("--","--");
                return new PasswordAuthentication("----w15719290065@126.com---", "----JTRNDUKEVKRUQNSH");
            }
        });
        return session;
    }
}
