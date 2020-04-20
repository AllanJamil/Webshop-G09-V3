package ejb;

import javax.ejb.Local;

@Local
public interface DatabaseBeanLocal {
    void fillDb();
}
