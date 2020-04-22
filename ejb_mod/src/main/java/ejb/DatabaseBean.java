package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "DatabaseEJB")
public class DatabaseBean implements DatabaseBeanLocal{
    public DatabaseBean() {
    }

    private List<Record> recordList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    @PersistenceContext(name = "webshopUnit")
    EntityManager em;


    @Override
    public void fillDb() {

        //RECORDS
        final Record r1 = new Record("Toto", "Old is new", "toto-1.jpg", 149);
        final Record r2 = new Record("Excalibur", "Live In Brocéliande", "excalibur-1.jpg", 209);
        final Record r3 = new Record("John Hiatt", "Ottawa", "johnhiatt-1.jpg", 199);
        final Record r4 = new Record("Streaplers", "En gång till", "streaplers-1.jpg", 79);
        this.recordList.add(r1);
        this.recordList.add(r2);
        this.recordList.add(r3);
        this.recordList.add(r4);

        //USERS
        final User u1 = new User("mrtest", "Test", "Testsson", "losen", Role.CUSTOMER);
        final User u2 = new User("jacob1", "Jacob", "Andersson", "password", Role.CUSTOMER);
        final User u3 = new User("sandra85", "Sandra", "Berg", "mittlosen", Role.CUSTOMER);
        final User u4 = new User("Larssa2000", "Lars", "Lerin", "Junior123", Role.PREMIUM);
        final User u5 = new User("HockeyDanne", "Daniel", "Sedin", "qwerty", Role.ADMIN);
        this.userList.add(u1);
        this.userList.add(u2);
        this.userList.add(u3);
        this.userList.add(u4);
        this.userList.add(u5);


        // ORDERINFO
        OrderInfoHistory oi1 = new OrderInfoHistory(r1, 6);
        OrderInfoHistory oi2 = new OrderInfoHistory(r2, 3000);
        OrderInfoHistory oi3 = new OrderInfoHistory(r2, 150);
        OrderInfoHistory oi4 = new OrderInfoHistory(r4, 15);
        OrderInfoHistory oi5 = new OrderInfoHistory(r3, 15);
        OrderInfoHistory oi6 = new OrderInfoHistory(r1, 15);
        List<OrderInfoHistory> orderInfoHistoryList1 = new ArrayList<>();
        List<OrderInfoHistory> orderInfoHistoryList2 = new ArrayList<>();
        List<OrderInfoHistory> orderInfoHistoryList3 = new ArrayList<>();

        orderInfoHistoryList1.add(oi1);
        orderInfoHistoryList1.add(oi2);
        orderInfoHistoryList2.add(oi3);
        orderInfoHistoryList2.add(oi4);
        orderInfoHistoryList2.add(oi5);
        orderInfoHistoryList3.add(oi6);

        //ORDER
        OrderHistory o1 = new OrderHistory(Date.valueOf(LocalDate.of(2020,1,3)));
        OrderHistory o2 = new OrderHistory(Date.valueOf(LocalDate.of(2019,11,15)));
        OrderHistory o3 = new OrderHistory(Date.valueOf(LocalDate.of(2019,8,27)));
        o1.setItems(orderInfoHistoryList1);
        o2.setItems(orderInfoHistoryList2);
        o3.setItems(orderInfoHistoryList3);
        o1.setUser(u4);
        o2.setUser(u1);
        o3.setUser(u2);

        List<OrderHistory> orders1 = new ArrayList<>();
        List<OrderHistory> orders2 = new ArrayList<>();
        orders1.add(o1);
        orders1.add(o2);
        orders2.add(o3);

        oi1.setOrder(o1);
        oi2.setOrder(o1);
        oi3.setOrder(o2);
        oi4.setOrder(o2);
        oi5.setOrder(o2);
        oi6.setOrder(o3);

        u4.setOrders(orders1);
        u1.setOrders(orders2);




        final TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r", Record.class);
        if (query.getResultList().size() == 0) {
            recordList.forEach(record -> em.persist(record));
            userList.forEach(user -> em.persist(user));
        }

    }
}
