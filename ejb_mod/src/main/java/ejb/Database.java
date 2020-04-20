package ejb;


import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private List<Record> recordList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    @PersistenceContext(name = "webshopUnit")
    EntityManager em;


    public void fillDb() {

        //RECORDS
        this.recordList.add(new Record("Toto", "Old is new", "toto-1.jpg", 149));
        this.recordList.add(new Record("Excalibur", "Live In Brocéliande", "excalibur-1.jpg", 209));
        this.recordList.add(new Record("John Hiatt", "Ottawa", "johnhiatt-1.jpg", 199));
        this.recordList.add(new Record("Streaplers", "En gång till", "streaplers-1.jpg", 79));

        //USERS
        this.userList.add(new User("mrtest", "Test", "Testsson", "lösen", Role.CUSTOMER));
        this.userList.add(new User("jacob1", "Jacob", "Andersson", "password", Role.CUSTOMER));
        this.userList.add(new User("sandra85", "Sandra", "Berg", "mittlösen", Role.CUSTOMER));
        this.userList.add(new User("Larssa2000", "Lars", "Lerin", "Junior123", Role.PREMIUM));
        this.userList.add(new User("HockeyDanne", "Daniel", "Sedin", "qwerty", Role.ADMIN));

        final TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r", Record.class);
        if (query.getResultList().size() == 0) {
            recordList.forEach(record -> em.persist(record));
            userList.forEach(user -> em.persist(user));
        }

    }
}
