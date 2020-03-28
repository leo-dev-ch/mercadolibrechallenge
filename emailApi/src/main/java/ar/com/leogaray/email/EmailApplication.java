package ar.com.leogaray.email;


import ar.com.leogaray.email.domain.email.EmailDTO;
import ar.com.leogaray.email.domain.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.mail.Address;
import javax.mail.Message;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EmailApplication {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(EmailApplication ctx) {
        return args -> {
            this.emailService.connect();
            //Message[] msgs = this.emailService.getMessages();
            String pattern = "DevOps";
            Message[] msgs = this.emailService.searchMessages(pattern);

            if (msgs == null)
                throw new Exception("Not result");

            if (msgs.length > 0) {
                List<EmailDTO> emailDtoList = new ArrayList<>();
                for (int i = 0; i < msgs.length; i++) {
                    EmailDTO emailDto = new EmailDTO();
                    emailDto.setSubject(msgs[i].getSubject());

                    Address[] addresses;
                    String from = "";
                    if ((addresses = msgs[i].getFrom()) != null) {
                        for (int j = 0; j < addresses.length; j++)
                            from += addresses[j].toString();
                    }
                    emailDto.setFrom(from);

                    Date sendDate = msgs[i].getSentDate();
                    if (sendDate != null) {
                        LocalDate localDate = sendDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        emailDto.setDate(localDate);
                    }
                    emailDtoList.add(emailDto);
                }
                this.emailService.closeFolder();
				this.emailService.disconnect();
                List<EmailDTO> emailListSave = this.emailService.saveList(emailDtoList);
            }
        };
    }

}
