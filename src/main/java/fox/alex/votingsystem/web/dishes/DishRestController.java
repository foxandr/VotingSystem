package fox.alex.votingsystem.web.dishes;

import fox.alex.votingsystem.model.Dish;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by fox on 19.09.16.
 */
@RestController
@RequestMapping(DishRestController.REST_URL)
public class DishRestController extends AbstractDishController {

    static final String REST_URL = "/rest/admin/dishes";

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @RequestParam("rest_id") int rest_id) {
        Dish newDish = super.create(dish, rest_id);
        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL + "/get").build().toUri();
        return ResponseEntity.created(uriOfNewResponse).body(newDish);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") int id, @RequestParam("rest_id") int rest_id) {
        super.delete(id, rest_id);
    }

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

    @RequestMapping(path = "/{rest_id}/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("rest_id") int rest_id) {
        super.update(dish, id, rest_id);
    }

}
