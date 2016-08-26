package fox.alex.votingsystem.testData;

import fox.alex.votingsystem.matcher.ModelMatcher;
import fox.alex.votingsystem.model.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    public static final Dish DISH1 = new Dish(DISH_ID, "Шашлык из говяжей вырезки", 12.0);
    public static final Dish DISH2 = new Dish(DISH_ID + 1, "Салат \"Белорусский\"", 9.5);
    public static final Dish DISH3 = new Dish(DISH_ID + 2, "Грибное лукошко", 9.0);
    public static final Dish DISH4 = new Dish(DISH_ID + 3, "Клубничный макарун", 6.5);
    public static final Dish DISH5 = new Dish(DISH_ID + 4, "Салат \"Цезарь\"", 6.9);
    public static final Dish DISH6 = new Dish(DISH_ID + 5, "Стейк из лосося", 15.4);
    public static final Dish DISH7 = new Dish(DISH_ID + 6, "Спагетти \"Наполетано\"",10.5);
    public static final Dish DISH8 = new Dish(DISH_ID + 7, "Салянка мясная", 8.8);
    public static final Dish DISH9 = new Dish(DISH_ID + 8, "Салат \"Греческий\"", 12.1);
    public static final Dish DISH10= new Dish(DISH_ID + 9, "Тигровые креветки", 20.4);
    public static final Dish DISH11= new Dish(DISH_ID + 10, "Картофель с грибами", 6.6);

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
