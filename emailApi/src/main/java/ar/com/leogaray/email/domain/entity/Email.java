package ar.com.leogaray.email.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "messages")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    private LocalDate date;
    @Column(name = "from_message")
    private String from;
    @Column(name = "subject")
    private String subject;

    public Email() {
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
}
