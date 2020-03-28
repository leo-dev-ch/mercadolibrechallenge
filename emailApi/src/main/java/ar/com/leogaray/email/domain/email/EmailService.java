package ar.com.leogaray.email.domain.email;

import ar.com.leogaray.email.domain.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService implements IEmailService {

    @Autowired
    protected EmailConverter emailConverter;
    @Autowired
    private IEmailRepository emailRepository;
    @Autowired
    private ConnectionEmail connectEmail;


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
}
