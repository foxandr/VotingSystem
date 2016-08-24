package fox.alex.votingsystem.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by fox on 11.08.16.
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@NamedEntityGraphs({
    @NamedEntityGraph(name = Restaurant.GRAPH_WITH_DISHES, attributeNodes = @NamedAttributeNode("dishes"))
})
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurants_idx")})
public class Restaurant extends BaseEntity{

    public static final String GRAPH_WITH_DISHES = "Restaurant.withDishes";

    @Column(name = "address", nullable = false)
    @NotEmpty
    @Length(max = 256)
    private String address;

    @Column(name = "registred", columnDefinition = "timestamp default now()")
    private Date registred = new Date();

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("price DESC")
    private List<Dish> dishes;

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

    public void setRegistred(Date registred) {
        this.registred = registred;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
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
