package fox.alex.votingsystem.to;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by fox on 05.09.16.
 */
public class UserTo extends BaseTo {

    @NotEmpty
    @Size(min = 6, max = 64, message = "must between 6 and 64 symbols")
    private String password;

    @NotEmpty
    @Email
    private String email;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String password, String email) {
        super(id, name);
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
