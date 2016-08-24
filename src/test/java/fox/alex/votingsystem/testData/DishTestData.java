package fox.alex.votingsystem.testData;

import fox.alex.votingsystem.matcher.ModelMatcher;
import fox.alex.votingsystem.model.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static fox.alex.votingsystem.model.BaseEntity.START_INDEX;

/**
 * Created by fox on 24.08.16.
 */
public class DishTestData {
    private static final Logger LOG = LoggerFactory.getLogger(DishTestData.class);

    public static final int DISH_ID = START_INDEX + 6;
    
    public static final Dish DISH1 = new Dish(DISH_ID, "Шашлык из говяжей вырезки", new BigDecimal(12).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH2 = new Dish(DISH_ID + 1, "Салат 'Белорусский'", new BigDecimal(9.5).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH3 = new Dish(DISH_ID + 2, "Грибное лукошко", new BigDecimal(9).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH4 = new Dish(DISH_ID + 3, "Клубничный макарун", new BigDecimal(6.5).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH5 = new Dish(DISH_ID + 4, "Салат 'Цезарь'", new BigDecimal(6.9).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH6 = new Dish(DISH_ID + 5, "Стейк из лосося", new BigDecimal(15.4).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH7 = new Dish(DISH_ID + 6, "Спагетти 'Наполетано'", new BigDecimal(10.5).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH8 = new Dish(DISH_ID + 7, "Салянка мясная", new BigDecimal(8.8).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH9 = new Dish(DISH_ID + 8, "Салат 'Греческий'", new BigDecimal(12.1).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH10= new Dish(DISH_ID + 9, "Тигровые креветки", new BigDecimal(20.4).setScale(1, RoundingMode.CEILING));
    public static final Dish DISH11= new Dish(DISH_ID + 10, "Картофель с грибами", new BigDecimal(6.6).setScale(1, RoundingMode.CEILING));

    public static final List<Dish> DISH_LIST = Arrays.asList(DISH11, DISH10, DISH9, DISH8, DISH7, DISH6, DISH5, DISH4, DISH3, DISH2, DISH1);

    public static final ModelMatcher<Dish> MATCHER = new ModelMatcher<>(Dish.class,
            (expected, actual) -> {
                if (expected == actual) {
                    return true;
                }
                boolean cmp = Objects.equals(expected.getId(), actual.getId())
                        && Objects.equals(expected.getName(), actual.getName())
                        && Objects.equals(expected.getPrice(), actual.getPrice());
                return cmp;
            }
    );
}
