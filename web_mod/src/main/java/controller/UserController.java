package controller;

import ejb.FetchDataBeanLocal;
import ejb.Record;
import ejb.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {


    private User currentUser;
    private List<Record> recordList;

    private List<CartItem> cartItems;
    private int cartLength;

    @EJB
    FetchDataBeanLocal fetchDataBeanLocal;

    @PostConstruct
    public void init() {
        recordList = fetchDataBeanLocal.getAllRecords();
        this.cartItems = new ArrayList<>();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getCartLength() {
        return cartLength;
    }

    public void setCartLength(int cartLength) {
        this.cartLength = cartLength;
    }

    public void addToCart(int id) {
        CartItem cartItem = getCartItemFromId(id);
        if (cartItem != null) {
            cartItem.setQty(cartItem.getQty() + 1);
        } else {
            for (Record record : recordList) {
                if (record.getId() == id) {
                    cartItems.add(new CartItem(record));
                }
            }
        }
        updateCartLength();
    }

    public void clearCart() {
        this.cartItems.clear();
        this.cartLength = 0;
    }

    public String logout() {
        clearCart();
        return "login";
    }

    public String getTotalCartSum() {
        int result = 0;
        for (CartItem cartItem:cartItems) {
            result += cartItem.getTotalPrice();
        }
        return String.valueOf(result);
    }

    private CartItem getCartItemFromId(int id) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getRecord().getId() == id) {
                return cartItem;
            }
        }
        return null;
    }

    private void updateCartLength() {
        int count = 0;
        for (CartItem cartItem : cartItems) {
            count += cartItem.getQty();
        }
        this.cartLength = count;
    }
}
