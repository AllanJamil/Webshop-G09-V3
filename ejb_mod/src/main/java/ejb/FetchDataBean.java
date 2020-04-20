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
        TypedQuery<User> userQuery = em.createQuery("SELECT o FROM User o where o.role = 0 or o.role = 1", User.class);
        return userQuery.getResultList();
    }
}
