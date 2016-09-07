package fox.alex.votingsystem.to;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by fox on 07.09.16.
 */
public class RestaurantTo extends BaseTo {

    @NotEmpty
    @Size(max = 256, message = "must less than 256 symbols")
    private String address;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ",address='" + address + '\'' +
                '}';
    }
}
