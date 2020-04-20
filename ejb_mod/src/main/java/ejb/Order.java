package ejb;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderInfo> items = new ArrayList<>();
    private LocalDate date;
    private int orderTotal;
    private int itemsQuantity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    public Order(LocalDate date) {
        this.date = date;
    }

    public Order() {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public List<OrderInfo> getItems() {
        return items;
    }

    public void setItems(List<OrderInfo> items) {
        this.items = items;
    }

    public void addNewProductToOrder(Record record, int quantity) {
        items.add(new OrderInfo(record, quantity));
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
}
