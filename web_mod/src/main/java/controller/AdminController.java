package controller;

import ejb.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
    FetchDataBeanLocal fetchDataBeanLocal;
    // Temporary variable for testing
    @EJB
    DatabaseBeanLocal databaseBeanLocal;

    @PostConstruct
    public void init() {
        databaseBeanLocal.fillDb();
    }

    public List<User> getCustomerList() {
        return this.customerList = fetchDataBeanLocal.fetchAllCustomers();
    }

    public String viewCustomerOrder(Long id) {
        this.orderHistoryList = fetchDataBeanLocal.fetchOrderByCustomerId(id);
        return "adminOrderOverview";
    }

    public List<OrderHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public String viewItemsInOrder(Long id) {
        this.itemList = fetchDataBeanLocal.fetchItemsByOrderId(id);
        return "adminItemOverview";
    }

    public List<OrderInfoHistory> getItemList() {
        return itemList;
    }
}
