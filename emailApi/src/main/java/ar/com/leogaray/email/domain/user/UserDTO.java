package ar.com.leogaray.email.domain.user;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 489799306143301328L;

    private String username;
    private String password;

    public UserDTO() {
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

}
