package ar.com.leogaray.email.domain.email;

import com.sun.mail.pop3.POP3SSLStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.mail.*;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

@Component
public class ConnectionEmail {

    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;
    private Session session = null;
    private Store store = null;
    private Folder folder;

    public ConnectionEmail() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void connect() throws Exception {

        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties pop3Props = new Properties();

        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

        URLName url = new URLName("pop3", "pop.gmail.com", 995, "", this.username, this.password);

        this.session = Session.getInstance(pop3Props, null);
        this.store = new POP3SSLStore(this.session, url);
        this.store.connect();
    }

    public void disconnect() throws Exception {
        store.close();
    }

    public void openFolder(String name) throws Exception {

        this.folder = this.store.getDefaultFolder();
        this.folder = this.folder.getFolder(name);

        if (folder == null) {
            throw new Exception("Invalid Folder.");
        }
        folder.open(Folder.READ_ONLY);
    }

    public void closeFolder() throws MessagingException {
        this.folder.close(false);
    }

    public Message[] getAllMessages() throws Exception {
        return folder.getMessages();
    }

    public Message[] searchMessages(String pattern) throws Exception {
        SearchTerm searchTerm = new AndTerm(new SubjectTerm(pattern), new BodyTerm(pattern));
        Message[] messages = this.folder.search(searchTerm);
        return  messages;
    }
}
