package controller;

import ejb.LoginBeanLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginController implements Serializable {

    private String name;
    private String message;

    @EJB
    LoginBeanLocal loginBeanLocal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void greet() {
        message = loginBeanLocal.test(name);
    }
}
