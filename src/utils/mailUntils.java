package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailUntils {
	
	private String toMail;
	private String subjectMail;
	private String bodyMail;

	public boolean guiEmail(){
        try {
            String email="minhiep18@gmail.com";
            String pass="sqdbxjnbxltzgevd";
            //cau hinh
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", 587);
            p.put("mail.smtp.ssl.trust", "*");
            // dang nhap
            
            Session s = Session.getInstance(p,
            new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(email,pass);
            }
            });
            
            // noi dung
            String from =email;
            String to =toMail;
            String subject =subjectMail;
            String body =bodyMail;
            
            MimeMessage msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            
            msg.setHeader("Content-Type", "text/plain; charset=utf-8");
            msg.setSubject(subject,"utf-8");
            msg.setText(body,"utf-8");
            System.out.println(subject+body);
            // gui
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public void setto(String mail) {
		toMail =mail;
	}
	public void setsubject(String subject) {
		subjectMail=subject;
	}
	public mailUntils(String toMail, String subjectMail, String bodyMail) {
		super();
		this.toMail = toMail;
		this.subjectMail = subjectMail;
		this.bodyMail = bodyMail;
	}

	public void setbody(String body) {
		bodyMail = body;
	}
}
