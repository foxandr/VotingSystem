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
public class DishAjaxController extends AbstractDishController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@Valid DishTo dishTo, @RequestParam("rest_id_upd") int rest_id) {
        try {
            if (dishTo.isNew()){
                super.create(DishUtil.createNewFromTo(dishTo), rest_id);
            } else {
                super.update(dishTo, dishTo.getId(), rest_id);
            }
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(messageSource.getMessage("exception.ddish", null, LocaleContextHolder.getLocale()));
        }
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") int id, @RequestParam("rest_id") int rest_id) {
        super.delete(id, rest_id);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
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

    @RequestMapping(path = "/getWithRest", method = RequestMethod.GET)
    public Dish getWithRerstaurant(@RequestParam("id") int id, @RequestParam("rest_id") int rest_id) {
        return super.getWithRerstaurant(id, rest_id);
    }

}
