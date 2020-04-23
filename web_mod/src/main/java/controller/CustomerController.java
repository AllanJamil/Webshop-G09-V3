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


    @EJB
    ReadDataBeanLocal fetchDataBean;
    @EJB
    ShoppingCartBeanLocal shoppingCart;

    @Inject
    LoginController loginController;

    CurrentUserBeanLocal currentUserBeanLocal;

    @PostConstruct
    public void ini() {
        this.currentUserBeanLocal = loginController.getCurrentUserBeanLocal();
    }

    public List<Record> getAllRecords() {
        return fetchDataBean.getAllRecords();
    }


    public int getCartLength() {
        return shoppingCart.getCartLength();
    }

    public List<CartItem> getCart() {
        return shoppingCart.getCart();
    }


    public String logout() {
        shoppingCart.clearCart();
        return "login";
    }

    public void addToCart(int id) {
        shoppingCart.addToCart(id);
    }

    public void clearCart() {
        shoppingCart.clearCart();
    }

    public String getTotalCartSum() {
        int totalCartSum = shoppingCart.getTotalCartSum(currentUserBeanLocal.getCurrentUser());
        return String.valueOf(totalCartSum);
    }

    public String shopAfterConfirmation() {
        clearCart();
        return "shop";
    }

    public String getUserFullName() {
        return currentUserBeanLocal.getUserFullName();
    }

}
