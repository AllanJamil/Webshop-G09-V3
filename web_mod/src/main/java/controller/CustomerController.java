package controller;

import ejb.*;
import ejb.Record;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CustomerController implements Serializable {

    private User currentUser;

    @EJB
    FetchDataBeanLocal fetchDataBean;
    @EJB
    ActiveCustomerBeanLocal loggedInUserBean;
    @Inject
    LoginController loginController;

    @PostConstruct
    public void ini() {
        this.currentUser = loginController.getCurrentUser();
    }

    public List<Record> getAllRecords() {
        return fetchDataBean.getAllRecords();
    }


    public int getCartLength() {
        return loggedInUserBean.getCartLength();
    }

    public List<CartItem> getCart() {
       return loggedInUserBean.getCart();
    }


    public String logout() {
        loggedInUserBean.clearCart();
        return "login";
    }

    public void addToCart(int id) {
        loggedInUserBean.addToCart(id);
    }

    public void clearCart() {
        loggedInUserBean.clearCart();
    }

    public String getTotalCartSum() {
        return String.valueOf(loggedInUserBean.getTotalCartSum());
    }

    public String shopAfterConfirmation() {
        clearCart();
        return "shop";
    }

    public String getUserFullName() {
       return loggedInUserBean.getUserFullName();
    }

    public ActiveCustomerBeanLocal getLoggedInUserBean() {
        return loggedInUserBean;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
