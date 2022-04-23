package Login;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

public class Mailer {

    final String User_Email = "noreplytest.gymvale@gmail.com"; //your email
    final String Password = "GymVale8068@"; // your email password
    final String Sender = "noreplytest.gymvale@gmail.com"; // Insert Your email again
    // Insert Receiver's Email
    final String Email_Subject = "Gym Vale - Forgot Username"; // Insert Email Subject


    public void Send_Email(String Receiver_mail,String Body)
    {
        final Session newsession = Session.getInstance(this.Mail_Properties(), new Authenticator()
        {
            @Override
            // password authentication
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(User_Email, Password);
            }
        });

        // MimeMessage is used to create the email message
        try
        {
            final Message Demo_Message = new MimeMessage(newsession);
            Demo_Message.setRecipient(Message.RecipientType.TO, new InternetAddress(Receiver_mail));
            Demo_Message.setFrom(new InternetAddress(Sender));
            Demo_Message.setSubject(Email_Subject); // email subject
            Demo_Message.setText(Body); // The content of email
            Demo_Message.setSentDate(new Date());
            Transport.send(Demo_Message);// Transport the email
            System.out.println("Your Email has been sent successfully!");
        } catch (final MessagingException e)
        { // exception to catch the errors
            System.out.println("Email Sending Failed"); // failed
            e.printStackTrace();
        }
    }

    // The permanent  set of properties containing string keys, the following
    // setting the properties for SMPT function
    public Properties Mail_Properties() {
        final Properties Mail_Prop = new Properties();
        Mail_Prop.put("mail.smtp.host", "smtp.gmail.com");
        Mail_Prop.put("mail.smtp.post", "587");
        Mail_Prop.put("mail.smtp.auth", true);
        Mail_Prop.put("mail.smtp.starttls.enable", true);
        Mail_Prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return Mail_Prop;
    }

}