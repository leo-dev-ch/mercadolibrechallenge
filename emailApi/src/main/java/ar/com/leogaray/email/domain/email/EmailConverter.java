package ar.com.leogaray.email.domain.email;

import ar.com.leogaray.email.domain.entity.Email;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class EmailConverter extends AbstractConverter<Email,EmailDTO> {

    @Override
    protected EmailDTO convert(Email email) {
        EmailDTO emailDto = new EmailDTO();
        emailDto.setId(email.getId());
        emailDto.setDate(email.getDate());
        emailDto.setFrom(email.getFrom());
        emailDto.setSubject(email.getSubject());
        return emailDto;
    }
    protected Email convertToDto(EmailDTO emailDto) {
        Email email = new Email();
        email.setId(emailDto.getId());
        email.setDate(emailDto.getDate());
        email.setFrom(emailDto.getFrom());
        email.setSubject(emailDto.getSubject());
        return email;
    }
}
