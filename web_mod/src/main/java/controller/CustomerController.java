package controller;

import ejb.*;
import ejb.Record;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.attribute.standard.Severity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    private CreateDataBeanLocal createDataBean;
    @EJB
    private ReadDataBeanLocal fetchDataBean;
    @EJB
    private ShoppingCartBeanLocal shoppingCart;

    @Inject
    private LoginController loginController;

    @EJB
    private CurrentUserBeanLocal currentUserBeanLocal;

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
        User user = fetchDataBean.fetchUserById(currentUserBeanLocal.getCurrentUser().getId());
        int totalCartSum = shoppingCart.getTotalCartSum(user);
        NumberFormat nf = NumberFormat.getInstance(new Locale("sv", "SE"));
        return nf.format(totalCartSum);
    }

    public String shopAfterEmptyCart() {
        clearCart();
        return "shop";
    }

    public String getUserFullName() {
        return currentUserBeanLocal.getUserFullName();
    }

    public String ConfirmOrder() {
        if (shoppingCart.getCartAsOrderInfoList().isEmpty()) {
            return "cart";
        } else {
            createDataBean.addNewOrder(currentUserBeanLocal.getCurrentUser(), shoppingCart.getCartAsOrderInfoList());
            User user = fetchDataBean.fetchUserById(currentUserBeanLocal.getCurrentUser().getId());
            createDataBean.premiumUpgrade(user);
            shoppingCart.clearCart();
            return "confirmation";
        }
    }

    public List<OrderInfoHistory> getOrderInfoList() {
        return shoppingCart.getOrderInfoHistoryList();
    }

    public String getTotPriceOrderInfoList() {
        User user = fetchDataBean.fetchUserById(currentUserBeanLocal.getCurrentUser().getId());
        return shoppingCart.getTotPriceFromOrderInfoList(user);
    }

    public String premium(){
        User user = fetchDataBean.fetchUserById(currentUserBeanLocal.getCurrentUser().getId());
        if(user.getRole() == Role.PREMIUM){
            return "Du är en Premium kund, du har 10% Rabatt i kassan";
        }
        return "";
    }
    public void updateQuantity(Long recordId, int quantity){
        shoppingCart.updateQuantity(recordId,quantity);
    }
}