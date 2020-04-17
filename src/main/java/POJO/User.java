package POJO;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
