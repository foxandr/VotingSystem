import fox.alex.votingsystem.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by fox on 11.08.16.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hello!");
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")){
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserService userService = appCtx.getBean(UserService.class);
            System.out.println(userService.get(1));
        }
    }
}
