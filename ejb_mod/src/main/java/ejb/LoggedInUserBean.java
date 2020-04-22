package ejb;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful(name = "LoggedInUserEJB")
public class LoggedInUserBean implements LoggedInUserBeanLocal{

    private User currentUser;
    private List<CartItem> cart;
    private int cartLength;

    @EJB
    FetchDataBeanLocal fetchDataBean;

    public LoggedInUserBean() {
        cart = new ArrayList<>();
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public User getCurrentUser() {
        return null;
    }


    public List<CartItem> getCart() {
        return this.cart;
    }

    public int getCartLength() {
        return cartLength;
    }

    public void setCartLength(int cartLength) {
        this.cartLength = cartLength;
    }

    @Override
    public void addToCart(int id) {
        CartItem cartItem = getCartItemFromId(id);
        if (cartItem != null) {
            cartItem.setQty(cartItem.getQty() + 1);
        } else {
            for (Record record : fetchDataBean.getAllRecords()) {
                if (record.getId() == id) {
                    cart.add(new CartItem(record));
                }
            }
        }
        updateCartLength();
    }
    @Override
    public void clearCart() {
        this.cart.clear();
        this.cartLength = 0;
    }
    @Override
    public CartItem getCartItemFromId(int id) {
        for (CartItem cartItem : cart) {
            if (cartItem.getRecord().getId() == id) {
                return cartItem;
            }
        }
        return null;
    }
    @Override
    public void updateCartLength() {
        int count = 0;
        for (CartItem cartItem : cart) {
            count += cartItem.getQty();
        }
        this.cartLength = count;
    }

    public int getTotalCartSum() {
        int result = 0;
        for (CartItem cartItem:cart) {
            result += cartItem.getTotalPrice();
        }
        return result;
    }

    @Override
    public String getUserFullName() {
        return currentUser.getFirstName() + " " + currentUser.getLastName();
    }
}
