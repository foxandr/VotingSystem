import fox.alex.votingsystem.utils.PasswordUtil;

/**
 * Created by fox on 11.08.16.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hello!");
        String pass = "megaadmin";
        String code = PasswordUtil.encode(pass);
        System.out.println(code);
        pass = "simpleuser";
        code = PasswordUtil.encode(pass);
        System.out.println(code);
        pass = "user123456";
        code = PasswordUtil.encode(pass);
        System.out.println(code);

    }
}
