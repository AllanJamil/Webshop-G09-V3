package ejb;

import javax.ejb.Stateless;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "DatabaseEJB")
public class CreateDataBean implements CreateDataBeanLocal {
    public CreateDataBean() {
    }
    @PersistenceContext(name = "webshopUnit")
    private EntityManager em;

    @Override
    public void fillDb() {

        if (em.createQuery("SELECT r FROM Record r", Record.class).getResultList().size() == 0) {

            List<Record> recordList = new ArrayList<>();
            List<User> userList = new ArrayList<>();

            //RECORDS
            final Record r1 = new Record("Toto", "Old is new", "toto-1.jpg", 149);
            final Record r2 = new Record("Excalibur", "Live In Brocéliande", "excalibur-1.jpg", 209);
            final Record r3 = new Record("John Hiatt", "Ottawa", "johnhiatt-1.jpg", 199);
            final Record r4 = new Record("Streaplers", "En gång till", "streaplers-1.jpg", 79);
            recordList.add(r1);
            recordList.add(r2);
            recordList.add(r3);
            recordList.add(r4);

            recordList.forEach(record -> em.persist(record));

            //USERS
            final User u1 = new User("mrtest", "Test", "Testsson", "losen", Role.CUSTOMER);
            final User u2 = new User("jacob1", "Jacob", "Andersson", "password", Role.CUSTOMER);
            final User u3 = new User("sandra85", "Sandra", "Berg", "mittlosen", Role.CUSTOMER);
            final User u4 = new User("Larssa2000", "Lars", "Lerin", "Junior123", Role.PREMIUM);
            final User u5 = new User("HockeyDanne", "Daniel", "Sedin", "qwerty", Role.ADMIN);
            userList.add(u1);
            userList.add(u2);
            userList.add(u3);
            userList.add(u4);
            userList.add(u5);

            userList.forEach(user -> em.persist(user));


            // ORDERINFO
            List<OrderInfoHistory> orderInfoHistoryList1 = new ArrayList<>();
            List<OrderInfoHistory> orderInfoHistoryList2 = new ArrayList<>();
            List<OrderInfoHistory> orderInfoHistoryList3 = new ArrayList<>();

            orderInfoHistoryList1.add(new OrderInfoHistory(r1, 6));
            orderInfoHistoryList1.add(new OrderInfoHistory(r2, 3000));
            orderInfoHistoryList2.add(new OrderInfoHistory(r2, 150));
            orderInfoHistoryList2.add(new OrderInfoHistory(r4, 15));
            orderInfoHistoryList2.add(new OrderInfoHistory(r3, 15));
            orderInfoHistoryList3.add(new OrderInfoHistory(r1, 3355));

            //ORDER
            addOrder(u4,orderInfoHistoryList1,LocalDate.of(2020, 1, 3));
            addOrder(u1,orderInfoHistoryList2,LocalDate.of(2019, 11, 15));
            addOrder(u2,orderInfoHistoryList3,LocalDate.of(2019, 8, 27));
        }
    }

    // metod för att fylla databas med fake data endast intern metod som kan vara private
    private void addOrder(User user, List<OrderInfoHistory> itemList, LocalDate date) {
        OrderHistory order = new OrderHistory(user,itemList,date);
        for (OrderInfoHistory oi: itemList) {
            oi.setOrder(order);
        }
        order.setItems(itemList);
        user.getOrders().add(order);
        order.setUser(user);
        em.persist(order);
    }

    /**
     * Metoden kan användas vid att lägga ny Order och kopplas till Controller
     * @param user
     * @param itemList
     */
    @Override
    public void addNewOrder(User user, List<OrderInfoHistory> itemList) {
        OrderHistory order = new OrderHistory(user,itemList,LocalDate.now());
        for (OrderInfoHistory oi: itemList) {
            oi.setOrder(order);
        }
        order.setItems(itemList);
        if(user.getRole() == Role.CUSTOMER && user.getTotalSpent() >= 500_000){
            em.merge(user);
        }
        order.setUser(user);
        em.persist(order);
    }
}