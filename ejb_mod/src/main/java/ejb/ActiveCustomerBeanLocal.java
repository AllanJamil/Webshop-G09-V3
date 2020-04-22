package ejb;

import java.util.List;

public interface ActiveCustomerBeanLocal {

    void addToCart(int id);
    void clearCart();
    CartItem getCartItemFromId(int id);
    void updateCartLength();
    int getTotalCartSum();
    String getUserFullName();
    void setCurrentUser(User currentUser);
    int getCartLength();
    List<CartItem> getCart();
}
