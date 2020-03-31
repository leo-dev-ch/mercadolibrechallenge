package ar.com.leogaray.email.domain.email;

import ar.com.leogaray.email.common.BusinessException;
import ar.com.leogaray.email.domain.user.UserDTO;
import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.pop3.POP3SSLStore;
import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.mail.*;
import javax.mail.search.*;

@Component
public class ConnectionMail {

    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private Integer port;
    @Value("${mail.protocol}")
    private String protocol;
    @Value("${mail.socketFactory.class}")
    private String socketFactory;
    @Value("${mail.socketFactory.fallback}")
    private String socketFactoryFallback;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    private Session session = null;
    private Store store = null;
    private Folder folder;
    private static final Logger logger = LoggerFactory.getLogger(ConnectionMail.class);

    public ConnectionMail() {
    }

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

    private Properties getProperties() {
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = new Properties();

        props.setProperty("mail.imap.socketFactory.class", this.socketFactory);
        props.setProperty("mail.imap.socketFactory.fallback", this.socketFactoryFallback);
        props.setProperty("mail.imap.port", String.valueOf(this.port));
        props.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
        return props;
    }

    public void connect(UserDTO userDto) throws Exception {
        try {
            Properties props = this.getProperties();
            URLName url = new URLName(this.protocol, this.host, this.port, "", userDto.getUsername(), userDto.getPassword());

            this.session = Session.getInstance(props, null);
            if (this.protocol.equals("imap"))
                this.store = new IMAPSSLStore(this.session, url);
            else if (this.protocol.equals("pop3"))
                this.store = new POP3SSLStore(this.session, url);

            this.store.connect();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new BusinessException("Ups..!!An error has occurred. " + exception.getMessage());
        }
    }

    public void connect() throws Exception {
        try {
            Properties props = this.getProperties();
            URLName url = new URLName(this.protocol, this.host, this.port, "", this.username, this.password);

            this.session = Session.getInstance(props, null);
            if (this.protocol.equals("imap"))
                this.store = new IMAPSSLStore(this.session, url);
            else if (this.protocol.equals("pop3"))
                this.store = new POP3SSLStore(this.session, url);

            this.store.connect();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new BusinessException("Ups..!!An error has occurred. " + exception.getMessage());
        }
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
        /*OrTerm use OR Contidional*/
        SearchTerm searchTerm = new OrTerm(new SearchTerm[] { new BodyTerm(pattern),new SubjectTerm(pattern) });
        Message[] messages = this.folder.search(searchTerm);
        return messages;
    }
}
