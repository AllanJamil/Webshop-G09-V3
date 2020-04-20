package controller;

import ejb.Database;
import ejb.LoginBeanLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private String name;
    private String message;

    @EJB
    LoginBeanLocal loginBeanLocal;

    @PostConstruct
    public void init() {
        System.out.println("HEJ");
    }

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
