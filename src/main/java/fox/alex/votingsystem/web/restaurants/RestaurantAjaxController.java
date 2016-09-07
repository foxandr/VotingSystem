package fox.alex.votingsystem.web.restaurants;

import fox.alex.votingsystem.model.Restaurant;
import fox.alex.votingsystem.to.RestaurantTo;
import fox.alex.votingsystem.utils.transfers.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by fox on 07.09.16.
 */
@RestController
@RequestMapping("/ajax/restaurants")
public class RestaurantAjaxController extends AbstractRestaurantConroller {

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@Valid RestaurantTo restaurantTo) {
        try {
            if (restaurantTo.isNew()) {
                super.create(RestaurantUtil.createNewFromTo(restaurantTo));
            } else {
                super.update(restaurantTo);
            }
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(messageSource.getMessage("exception.drest", null, LocaleContextHolder.getLocale()));
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/by", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getByName(@RequestParam("name") String name) {
        return super.getByName(name);
    }

    @RequestMapping(value = "/{id}/dishes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getWithDishes(@PathVariable("id") int id) {
        return super.getWithDishes(id);
    }
}
