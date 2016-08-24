package fox.alex.votingsystem.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by fox on 11.08.16.
 */
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"rest_id", "name"}, name = "dishes_idx")})
public class Dish extends BaseEntity{

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 10000)
    @NotNull
    private Double price;

    @Column(name = "updated", columnDefinition = "timestamp default now()")
    private Date updated = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    private Restaurant restaurant;

    public Dish() {}

    public Dish(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Integer id, String name, Double price, Date updated) {
        this(id, name, price);
        setUpdated(updated);
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getUpdated());
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = (updated == null) ? new Date() : updated;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ",name=" + name +
                ", price=" + price +
                ", updated=" + updated +
                '}';
    }
}
