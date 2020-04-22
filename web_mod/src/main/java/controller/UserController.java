package controller;

import ejb.*;
import ejb.Record;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @EJB
    FetchDataBeanLocal fetchDataBean;

    @EJB
    ActiveCustomerBeanLocal loggedInUserBean;

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

}
