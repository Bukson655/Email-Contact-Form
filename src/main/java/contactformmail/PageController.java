package contactformmail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class PageController {

    MailService mailFromFormService;

    public PageController(MailService mailService) {
        this.mailFromFormService = mailService;
    }

    @GetMapping("/")
    String home() {
        return "home";
    }

    @GetMapping("/kontakt")
    String contact() {
        return "contact";
    }

    @PostMapping("/wyslij")
    String sendContactFormByEmail(@RequestParam String name,
                                  @RequestParam String email,
                                  @RequestParam String content,
                                  @RequestParam (required = false) boolean confirmation,
                                  @Value("${support.mail}") String supportEmail) {
        mailFromFormService.sendToSupport(name, email, content, confirmation, supportEmail);
        return "form-confirmation";
    }

    @GetMapping("/wyslij")
    String returnToMainPage() {
        return "redirect:/";
    }
}