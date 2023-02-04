package ar.com.leogaray.email.domain.email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 489799306143301328L;

    private Long id;
    private LocalDate date;
    private String from;
    private String subject;

    public EmailDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDTO emailDTO = (EmailDTO) o;
        return Objects.equals(id, emailDTO.id) &&
                Objects.equals(date, emailDTO.date) &&
                Objects.equals(from, emailDTO.from) &&
                Objects.equals(subject, emailDTO.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, from, subject);
    }
}
