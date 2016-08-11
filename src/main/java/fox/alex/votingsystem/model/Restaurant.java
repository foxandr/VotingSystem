package fox.alex.votingsystem.model;

import java.util.Date;

/**
 * Created by fox on 11.08.16.
 */
public class Restaurant extends BaseEntity{

    private String address;

    private Date registred = new Date();

    public Restaurant() {}

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistred() {
        return registred;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                ", address='" + address + '\'' +
                ", registred=" + registred +
                '}';
    }
}
