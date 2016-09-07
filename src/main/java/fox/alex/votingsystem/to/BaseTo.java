package fox.alex.votingsystem.to;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by fox on 07.09.16.
 */
public class BaseTo implements Serializable {
    protected static final long serialVersionUID = 1L;

    protected Integer id;

    @NotEmpty
    protected String name;

    public BaseTo() {}

    public BaseTo(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public boolean isNew () {
        return (this.id == null);
    }
}
