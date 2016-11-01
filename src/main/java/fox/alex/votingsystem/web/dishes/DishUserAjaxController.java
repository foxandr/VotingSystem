package fox.alex.votingsystem.web.dishes;

import fox.alex.votingsystem.model.Dish;
import fox.alex.votingsystem.to.DishTo;
import fox.alex.votingsystem.utils.transfers.DishUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 19.09.16.
 */
@RestController
@RequestMapping("/ajax/dishes")
public class DishUserAjaxController extends AbstractDishController {

    @RequestMapping(path = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@RequestParam("id") int id, @RequestParam("rest_id") int rest_id) {
        return super.get(id, rest_id);
    }

    @RequestMapping(path = "/{rest_id}/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable("rest_id") int rest_id) {
        return super.getAll(rest_id);
    }

    @RequestMapping(path = "/getByDate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getByDate(@RequestParam("rest_id") int rest_id, @RequestParam("updated") LocalDate updated) {
        return super.getByDate(rest_id, updated);
    }

    @RequestMapping(path = "/getWithRest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getWithRerstaurant(@RequestParam("id") int id, @RequestParam("rest_id") int rest_id) {
        return super.getWithRerstaurant(id, rest_id);
    }

}
