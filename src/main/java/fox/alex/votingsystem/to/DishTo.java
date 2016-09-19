package fox.alex.votingsystem.to;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;

/**
 * Created by fox on 19.09.16.
 */
public class DishTo extends BaseTo {

    @NotEmpty
    @Digits(integer = 6, fraction = 2)
    @Range(min = 0, max = 10000)
    private Double price;

    public DishTo() {
    }

    public DishTo(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ",price=" + price + '\'' +
                '}';
    }
}
