package ejb;

import entity.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface FetchDataBeanLocal {
    List<User> fetchAllCustomers();
}