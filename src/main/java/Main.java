import fox.alex.votingsystem.model.Role;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by fox on 11.08.16.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hello!");
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")){
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserService userService = appCtx.getBean(UserService.class);
            System.out.println(userService.save(new User(15, "NEWUSER", "111@111.by", "bggg123456", new HashSet<Role>(Arrays.asList(Role.ROLE_USER)))));
            System.out.println(userService.save(new User(10, "NEWUSER1111", "1112@111.by", "bggg123456", new HashSet<Role>(Arrays.asList(Role.ROLE_USER)))));
            System.out.println(userService.get(10));
        }
    }
}
