package userAndApi;

public class User {

    private String email;
    private String password;
    private String name;

    public User(){ }

    /**
     * create user
     */
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    /**
     * user email
     */
    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
