package ar.com.leogaray.email.domain.email;

import ar.com.leogaray.email.common.BusinessException;
import ar.com.leogaray.email.domain.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmailService implements IEmailService {

    @Autowired
    protected EmailConverter emailConverter;
    @Autowired
    private IEmailRepository emailRepository;
    @Autowired
    private ConnectionMail connectEmail;


    @Override
    public void connect() throws Exception {
        this.connectEmail.connect();
    }

    @Override
    public void disconnect() throws Exception {
        this.connectEmail.disconnect();
    }

    @Override
    public Message[] getMessages() throws Exception {
        this.connectEmail.openFolder("INBOX");
        Message[] messages = this.connectEmail.getAllMessages();
        return messages;
    }

    @Override
    public Message[] searchMessages(String pattern) throws Exception {
        this.connectEmail.openFolder("INBOX");
        Message[] messages = this.connectEmail.searchMessages(pattern);
        return messages;
    }

    @Override
    public Message[] getMessages(String folder) throws Exception {
        this.connectEmail.openFolder(folder);
        Message[] messages = this.connectEmail.getAllMessages();
        return messages;
    }
    public void closeFolder() throws MessagingException {
        this.connectEmail.closeFolder();
    }

    @Override
    public EmailDTO saveEmail(EmailDTO emailDTO) {
        Email email = this.emailConverter.convertToDto(emailDTO);
        email = this.emailRepository.save(email);

        return this.emailConverter.convert(email);
    }

    @Override
    public List<EmailDTO> saveList(List<EmailDTO> emailDtoList) {
        List<EmailDTO> emailList = new ArrayList<>();
        for (EmailDTO dto : emailDtoList) {
            dto = this.saveEmail(dto);
            emailList.add(dto);
        }
        return emailList;
    }

    @Override
    public List<EmailDTO> convertMessages(Message[] messages) throws Exception {
        List<EmailDTO> emailDtoList = new ArrayList<>();

        if (messages.length > 0) {
            for(Message message : messages){
                EmailDTO emailDto = new EmailDTO();
                emailDto.setSubject(message.getSubject());

                Address[] addresses;
                String from = "";
                if ((addresses = message.getFrom()) != null) {
                    for (int j = 0; j < addresses.length; j++)
                        from += addresses[j].toString();
                }
                emailDto.setFrom(from);

                Date sendDate = message.getSentDate();
                if (sendDate != null) {
                    LocalDate localDate = sendDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    emailDto.setDate(localDate);
                }
                emailDtoList.add(emailDto);
            }
            this.closeFolder();
            this.disconnect();
        }

        return emailDtoList;
    }

    @Override
    public List<EmailDTO> searchAndSave(String pattern) throws Exception {
        this.connect();
        Message[] msgs = this.searchMessages(pattern);
        if (msgs == null)
            throw new BusinessException("Not result");

        List<EmailDTO> list = this.convertMessages(msgs);
        return this.saveList(list);
    }

}
