package ejb;

import javax.ejb.Local;

@Local
public interface LoginBeanLocal {
    boolean validateUser(String userName, String password);

    String test(String name);
}
