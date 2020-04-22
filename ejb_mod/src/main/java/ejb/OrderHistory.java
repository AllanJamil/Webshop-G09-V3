package ejb;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class OrderHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "order")
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
     *
     * @return: summan för en beställning
     */
    public String calculateOrderTotal() {
        int sum = items.parallelStream().mapToInt(item -> item.getQuantity() * item.getRecord().getPrice()).sum();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv","SE"));
        String formmated = nf.format(sum);
        return formmated.substring(0,formmated.lastIndexOf(",")) + " SEK";
    }

    /**
     * Metoden räknar ut antalet produkter i en order
     *
     * @return: antalet produkter i en order
     */
    public String calculateItemsQuantity() {
        int sum = items.parallelStream().mapToInt(item -> item.getQuantity()).sum();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv","SE"));
        String formmated = nf.format(sum);
        return formmated.substring(0,formmated.lastIndexOf(","));
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
