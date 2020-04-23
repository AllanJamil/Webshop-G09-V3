package ejb;

import java.util.List;

public interface ShoppingCartBeanLocal {

    void addToCart(int id);
    void clearCart();
    CartItem getCartItemFromId(int id);
    void updateCartLength();
    int getTotalCartSum(User user);
    int getCartLength();
    List<CartItem> getCart();
}
