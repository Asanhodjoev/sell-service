package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.services.SendSimpleMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendSimpleMessageImpl implements SendSimpleMassage {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMassage(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kuttubekasanhodzoev@gmail.com");
        message.setTo(to);
        message.setSubject("Код подтверждения");
        message.setText("Ваш код подтверждения "+text);
        mailSender.send(message);
    }
}
