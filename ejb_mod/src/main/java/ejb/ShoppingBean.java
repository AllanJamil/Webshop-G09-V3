package ejb;

import javax.ejb.Stateless;

@Stateless(name = "ShoppingEJB")
public class ShoppingBean implements ShoppingBeanLocal{
    public ShoppingBean() {
    }
}
