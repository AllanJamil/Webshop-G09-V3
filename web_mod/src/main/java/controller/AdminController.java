package controller;

import ejb.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminController implements Serializable {
    private User currentUser;
    private List<User> customerList;
    private List<OrderHistory> orderHistoryList;
    private List<OrderInfoHistory> itemList;

    @EJB
    ReadDataBeanLocal readDataBeanLocal;
    @EJB
    ShoppingCartBeanLocal activeCustomerBeanLocal;
    @Inject
    LoginController loginController;

    @PostConstruct
    public void init() {
        this.currentUser = loginController.getCurrentUser();
    }

    public List<User> getCustomerList() {
        return this.customerList = readDataBeanLocal.fetchAllCustomers();
    }

    public String viewCustomerOrder(Long id) {
        this.orderHistoryList = readDataBeanLocal.fetchOrderByCustomerId(id);
        return "adminOrderOverview";
    }

    public List<OrderHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public String viewItemsInOrder(Long id) {
        this.itemList = readDataBeanLocal.fetchItemsByOrderId(id);
        return "adminItemOverview";
    }

    public List<OrderInfoHistory> getItemList() {
        return itemList;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
