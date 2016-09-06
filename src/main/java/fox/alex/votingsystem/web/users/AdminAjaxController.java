package fox.alex.votingsystem.web.users;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.transfers.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by fox on 05.09.16.
 */
@RestController
@RequestMapping("/ajax/users")
public class AdminAjaxController extends AbstractUserController {

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@Valid UserTo userTo) {
        try {
            if (userTo.isNew()){
                super.create(UserUtil.createNewFromTo(userTo));
            } else {
                super.update(userTo);
            }
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(messageSource.getMessage("exception.demail", null, LocaleContextHolder.getLocale()));
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") int id) {
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email) {
        return super.getByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}/voices",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getWithVoices(@PathVariable("id") int id) {
        return super.getWithVoices(id);
    }
}
