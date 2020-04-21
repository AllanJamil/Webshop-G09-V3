package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "FetchDataEJB")
public class FetchDataBean implements FetchDataBeanLocal {

    @PersistenceContext(name = "webshopUnit")
    EntityManager em;

    public FetchDataBean() {
    }

    @Override
    public List<User> fetchAllCustomers() {
        TypedQuery<User> userQuery = em.createQuery("SELECT o FROM User o WHERE o.role = 0 OR o.role = 1", User.class);
        return userQuery.getResultList();
    }

    @Override
    public List<OrderHistory> fetchOrderByCustomerId(Long id) {
        TypedQuery<OrderHistory> orderQuery = em.createQuery("SELECT o FROM OrderHistory o WHERE o.user.id =" + " :id", OrderHistory.class).setParameter("id", id);
        return orderQuery.getResultList();
    }


}
