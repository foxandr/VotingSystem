package fox.alex.votingsystem.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by fox on 11.08.16.
 */
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"rest_id", "name"}, name = "dishes_idx")})
public class Dish extends BaseEntity{

    @Column(name = "price", nullable = false, precision = 6, scale = 2)
    @Range(min = 1, max = 10000)
    @NotNull
    private Double price;

    @Column(name = "updated", columnDefinition = "timestamp default current_date")
    private LocalDate updated = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    private Restaurant restaurant;

    public Dish() {}

    public Dish(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Integer id, String name, Double price, LocalDate updated) {
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

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = (updated == null) ? LocalDate.now() : updated;
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
