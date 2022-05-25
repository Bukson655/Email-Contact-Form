package contactformmail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
class MailService {

    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage mail;

    public MailService(JavaMailSender javaMailSender, SimpleMailMessage mail) {
        this.javaMailSender = javaMailSender;
        this.mail = mail;
    }

    void sendToSupport(String name, String email, String content, boolean confirmation, String supportEmail) {

        if (confirmation) {
            sendBackConfirmation(name, email, content, supportEmail);
        }
        sendMailFromFormToSupport(name, email, content, supportEmail);
    }

    private void sendMailFromFormToSupport(String name, String sender, String content, String supportEmail) {
        mail.setFrom(sender);
        mail.setSubject("Formularz kontaktowy od: " + name + " - " + sender);
        mail.setReplyTo(sender);
        mail.setText(content);
        mail.setTo(supportEmail);
        javaMailSender.send(mail);
    }

    private void sendBackConfirmation(String name, String receiver, String content, String supportMail) {
        String replyMessage = name + ",\nTwój formularz został pomyślnie wysłany do naszego działu pomocy."
                + "Wkrótce ktoś się z Tobą skontaktuje."
                + "\nJeżeli to nie Ty, zignoruj tę wiadomość."
                + "\nTreść formularza: \n\n"
                + content
                + "\n \n"
                + "Pozdrawiam, Automat Dunder Mifflin";

        mail.setFrom(supportMail);
        mail.setSubject("Potwierdzenie");
        mail.setText(replyMessage);
        mail.setTo(receiver);
        javaMailSender.send(mail);
    }
}