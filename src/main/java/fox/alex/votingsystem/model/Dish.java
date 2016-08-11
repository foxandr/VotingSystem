package fox.alex.votingsystem.model;

import java.util.Date;

/**
 * Created by fox on 11.08.16.
 */
public class Dish extends BaseEntity{

    protected Integer rest_id;

    private Integer price;

    private Date updated = new Date();

    public Dish() {}

    public Dish(Integer id, Integer rest_id, String name, Integer price) {
        super(id, name);
        this.rest_id = rest_id;
        this.price = price;
    }

    public Dish(Integer id, Integer rest_id, String name, Integer price, Date updated) {
        this(id, rest_id, name, price);
        setUpdated(updated);
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getRest_id(), dish.getName(), dish.getPrice(), dish.getUpdated());
    }

    public Integer getRest_id() {
        return rest_id;
    }

    public void setRest_id(Integer rest_id) {
        this.rest_id = rest_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = (updated == null) ? new Date() : updated;
    }

    @Override
    public int hashCode() {
        int result = (this.rest_id == null) ? 0 : this.rest_id;
        result = 37 * result + this.name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish other = (Dish) o;
        if (this.id == null || other.id == null) return false;
        if (this.rest_id != null || other.rest_id != null) {
            if (this.rest_id.equals(other.rest_id)) {
                if (this.name != null && other.name != null && this.name.equals(other.name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ",rest_id=" + rest_id +
                ",name=" + name +
                ", price=" + price +
                ", updated=" + updated +
                '}';
    }
}
