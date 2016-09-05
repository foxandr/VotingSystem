package fox.alex.votingsystem.to;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by fox on 05.09.16.
 */
public class UserTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 6, max = 64, message = "must between 6 and 64 symbols")
    private String password;

    @NotEmpty
    @Email
    private String email;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNew () {
        return (this.id == null);
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
