package com.murongyehua.mrwb.commom.util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @author murongyehua
 * @version 1.0 2019/8/3
 */
public class SendEmailUtil {
    /**
     * 邮件发送的方法
     *
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param smtp 协议
     * @param host 发送服务器服务器
     * @param sendName 邮件发送人
     * @param sendPort 邮件发送人端口
     * @param userName 邮件发送人名
     * @param userPwd 邮件发送人密码
     * @return 成功或失败
     */
    public static boolean send(String to, String subject, String content, String smtp, String host,String nickName,
                               String sendName, String sendPort, String userName, String userPwd) {

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = new Properties();
        // 指定邮件的传输协议
        props.put("mail.transport.protocol", smtp);
        // 指定邮件的发送人
        props.put("mail.from", sendName);
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", sendPort);
        props.setProperty("mail.smtp.socketFactory.port", sendPort);
        props.put("mail.smtp.auth", "true");
        // 创建Session
        Session session = Session.getDefaultInstance(props);

        // 开启调试模式
        session.setDebug(true);
        try {
            // 获取邮件发送对象
            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect(userName, userPwd);
            Address[] toAddress = InternetAddress.parse(to);

            // 创建邮件消息体
            MimeMessage message = new MimeMessage(session);
            //设置自定义发件人昵称
            String nick="";
            try {
                nick=javax.mail.internet.MimeUtility.encodeText(nickName);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setFrom(new InternetAddress(nick+" <"+sendName+">"));
            // 邮件的主题
            message.setSubject(subject);
            // 收件人
            message.addRecipients(Message.RecipientType.TO, toAddress);
            // 邮件的内容
            message.setContent(content, "text/html;charset=utf-8");
            // 邮件发送时间
            message.setSentDate(new Date());

            // 发送邮件
            // 第一个参数：邮件的消息体
            // 第二个参数：邮件的接收人，多个接收人用逗号隔开
            transport.sendMessage(message, toAddress);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
