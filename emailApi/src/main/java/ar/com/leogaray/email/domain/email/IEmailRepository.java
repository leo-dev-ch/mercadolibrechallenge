package ar.com.leogaray.email.domain.email;

import ar.com.leogaray.email.domain.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmailRepository extends JpaRepository<Email,Long> {
}
