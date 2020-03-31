package ar.com.leogaray.email.domain.email;


import ar.com.leogaray.email.domain.user.UserDTO;

import javax.mail.Message;
import java.util.List;

public interface IEmailService {


    void connect() throws Exception;

    void connect(UserDTO userDto) throws Exception;

    void disconnect() throws Exception;

    Message[] getMessages() throws Exception;

    Message[] searchMessages(String patter) throws Exception;

    Message[] getMessages(String folder) throws Exception;

    List<EmailDTO> saveList(List<EmailDTO> emailDtoList);

    EmailDTO saveEmail(EmailDTO emailDto);

    List<EmailDTO> convertMessages(Message[] messages) throws Exception;

    List<EmailDTO> searchAndSave(String pattern) throws Exception;

    List<EmailDTO> searchAndSaveByUser(UserDTO userDto, String pattern) throws Exception;
}
