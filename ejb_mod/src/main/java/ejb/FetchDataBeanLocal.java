package ejb;

import javax.ejb.Local;
import java.util.List;

@Local
public interface FetchDataBeanLocal {
    List<User> fetchAllCustomers();

    List<OrderHistory> fetchOrderByCustomerId(Long id);
}