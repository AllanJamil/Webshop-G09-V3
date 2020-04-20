package controller;

import ejb.DatabaseBeanLocal;
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
    @EJB
    DatabaseBeanLocal databaseBeanLocal;

    @PostConstruct
    public void init() {
        databaseBeanLocal.fillDb();
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
    }
}
