package ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless(name = "LoginEJB")
public class LoginBean implements LoginBeanLocal {
        @PersistenceContext(name = "webshopUnit")
        EntityManager entityManager;

    private String name;

    public LoginBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean validateUser(String userName, String password) {
        return false;
    }

    @Override
    public String test(String name) {
        return "Hello " +  name + "!";
    }
}

