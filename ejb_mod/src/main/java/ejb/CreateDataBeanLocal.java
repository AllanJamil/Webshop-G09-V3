package ejb;

import javax.ejb.Local;

@Local
public interface CreateDataBeanLocal {
    void fillDb();
}
