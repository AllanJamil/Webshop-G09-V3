package controller;

import ejb.*;
import ejb.Record;

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


    private List<Record> recordList;


    @EJB
    FetchDataBeanLocal fetchDataBean;

    @EJB
    LoggedInUserBeanLocal loggedInUserBean;

    @PostConstruct
    public void init() {
        recordList = fetchDataBean.getAllRecords();
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public int getCartLength() {
        return loggedInUserBean.getCartLength();
    }

    public List<CartItem> getCart() {
       return loggedInUserBean.getCart();
    }


    public String logout() {
        loggedInUserBean.clearCart();
        return "login";
    }

    public void addToCart(int id) {
        loggedInUserBean.addToCart(id);
    }

    public void clearCart() {
        loggedInUserBean.clearCart();
    }

    public String getTotalCartSum() {
        return String.valueOf(loggedInUserBean.getTotalCartSum());
    }

    public String getUserFullName() {
       return loggedInUserBean.getUserFullName();
    }

    public LoggedInUserBeanLocal getLoggedInUserBean() {
        return loggedInUserBean;
    }

    public void setLoggedInUserBean(LoggedInUserBeanLocal loggedInUserBean) {
        this.loggedInUserBean = loggedInUserBean;
    }
}
