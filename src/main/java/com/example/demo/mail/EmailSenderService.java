package com.example.demo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 由于Spring Boot的starter模块提供了自动化配置，
 * 所以在引入了spring-boot-starter-mail依赖之后，
 * 会根据配置文件中的内容去创建JavaMailSender实例，
 * 因此我们可以直接在需要使用的地方直接@Autowired来引入邮件发送对象。
 * <p>
 * http://blog.didispace.com/springbootmailsender/
 **/
@Service
public class EmailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${mail.send.to}")
    private String defaultSendTo;

    public void sendToDefault(String subject, String html) {
        this.send(defaultSendTo, subject, html);
    }

    public void send(String to, String subject, String html) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setFrom("SPAR<" + mailUsername + ">");
            helper.setTo(to);
            helper.setSubject(subject); //主题
            helper.setText(html, true); //正文
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("Send " + html + " error", e);
        }
    }

    public void send(String to, String subject, String html, File file) throws Exception {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        try {
            helper.setFrom("SPAR<" + mailUsername + ">");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            if (file != null) {
                helper.addAttachment(file.getName(), file);
            }
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("Send " + html + " error", e);
        }
    }

    public void send(String to, String subject, String html, DataSource attachment, String fileName) throws Exception {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        try {
            helper.setFrom("SPAR<" + mailUsername + ">");
            helper.setTo(new String[]{to});                             //收件人
            helper.setCc(new String[]{"抄送1", "抄送2", "抄送3"});      //抄送人
            helper.setBcc(new String[]{"密送1", "密送2", "密送3"});     //密送人
            helper.setSubject(subject);
            helper.setText(html, true);
            if (attachment != null) {
                //附件
//            FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
//            helper.addAttachment("附件-1.jpg", file);
//            helper.addAttachment("附件-2.jpg", file);
                helper.addAttachment(fileName, attachment);
            }
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            logger.error("Send " + html + " error", e);
        }
    }

}
