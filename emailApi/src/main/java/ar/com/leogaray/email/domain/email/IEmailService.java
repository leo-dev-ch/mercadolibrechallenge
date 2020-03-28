package ar.com.leogaray.email.domain.email;


import javax.mail.Message;
import java.util.List;

public interface IEmailService {


    void connect() throws Exception;

    void disconnect() throws Exception;

    Message[] getMessages() throws Exception;

    Message[] searchMessages(String patter) throws Exception;

    Message[] getMessages(String folder) throws Exception;

    List<EmailDTO> saveList(List<EmailDTO> emailDtoList);

    EmailDTO saveEmail(EmailDTO emailDto);
}
