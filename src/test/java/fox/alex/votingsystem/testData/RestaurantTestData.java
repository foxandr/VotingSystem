package fox.alex.votingsystem.testData;

import fox.alex.votingsystem.matcher.ModelMatcher;
import fox.alex.votingsystem.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static fox.alex.votingsystem.model.BaseEntity.START_INDEX;

/**
 * Created by fox on 24.08.16.
 */
public class RestaurantTestData {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantTestData.class);

    public static final int REST_ID1 = START_INDEX + 3;
    public static final int REST_ID2 = START_INDEX + 4;
    public static final int REST_ID3 = START_INDEX + 5;

    public static final Restaurant REST1 = new Restaurant(REST_ID1, "Кронон", "Пышки 1");
    public static final Restaurant REST2 = new Restaurant(REST_ID2, "Семашко", "Антонова 10");
    public static final Restaurant REST3 = new Restaurant(REST_ID3, "Карчма", "Советская 31");

    public static final List<Restaurant> RESTAURANT_LIST = Arrays.asList(REST3, REST1, REST2);

    public static final ModelMatcher<Restaurant> MATCHER = new ModelMatcher<>(Restaurant.class,
            (expected, actual) -> {
                if (expected == actual) {
                    return true;
                }
                boolean cmp = Objects.equals(expected.getId(), actual.getId())
                                && Objects.equals(expected.getName(), actual.getName())
                                && Objects.equals(expected.getAddress(), actual.getAddress());
                return cmp;
            }
    );
}
