package mattias.andersson.onky;

/**
 * Created by Mattias on 2015-06-16.
 */
public class createUser {
    private String username;
    private String password;

    public createUser() {

    }

    public createUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
