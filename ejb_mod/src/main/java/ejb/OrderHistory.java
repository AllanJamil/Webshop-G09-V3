package ejb;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderInfoHistory> items = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    //private LocalDate date;

    public OrderHistory(/*LocalDate date*/) {
        //this.date = date;
    }


    public Long getId() {
        return id;
    }

    public List<OrderInfoHistory> getItems() {
        return items;
    }

    public void setItems(List<OrderInfoHistory> items) {
        this.items = items;
    }

    public void addNewProductToOrder(Record record, int quantity) {
        items.add(new OrderInfoHistory(record, quantity));
    }


    /**
     * Metoden kalkylerar en beställnings total summa
     * @return: summan för en beställning
     */
    public int getOrderTotal() {
        return items.parallelStream().mapToInt(item -> item.getQuantity() * item.getRecord().getPrice()).sum();
    }

    /**
     * Metoden räknar ut antalet produkter i en order
     * @return: antalet produkter i en order
     */
    public int getItemsQuantity() {
        return items.parallelStream().mapToInt(item -> item.getQuantity()).sum();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
