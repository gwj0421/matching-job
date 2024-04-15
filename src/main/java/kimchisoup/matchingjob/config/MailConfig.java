package kimchisoup.matchingjob.config;

import kimchisoup.matchingjob.config.properties.MailProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    private final MailProperty mailProperty;

    public MailConfig(MailProperty mailProperty) {
        this.mailProperty = mailProperty;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperty.getHost());
        mailSender.setPort(mailProperty.getPort());

        mailSender.setUsername(mailProperty.getUsername());
        mailSender.setPassword(mailProperty.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProperty.getProtocol());
        props.put("mail.smtp.auth", mailProperty.getAuth());
        props.put("mail.smtp.starttls.enable", mailProperty.getStarttls());
//        props.put("mail.debug", "true");

        return mailSender;
    }

}
