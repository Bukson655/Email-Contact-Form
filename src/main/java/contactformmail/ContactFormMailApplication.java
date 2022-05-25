package contactformmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;

@SpringBootApplication
public class ContactFormMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactFormMailApplication.class, args);
    }

    @Bean
    SimpleMailMessage mail() {
        return new SimpleMailMessage();
    }
}