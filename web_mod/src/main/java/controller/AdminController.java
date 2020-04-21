package controller;

import ejb.DatabaseBeanLocal;
import ejb.FetchDataBeanLocal;
import entity.User;

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
}
