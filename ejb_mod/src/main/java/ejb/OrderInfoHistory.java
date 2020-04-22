package ejb;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
public class OrderInfoHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Record record;
    private int quantity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private OrderHistory order;

    public OrderInfoHistory(Record record, int quantity) {
        this.record = record;
        this.quantity = quantity;
    }

    public OrderInfoHistory() {

    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Metoden räknar ut summan av antalet av en specifik produkt
     * @return: summa
     */
    public String calculateTotalPrice() {
        int sum = quantity * record.getPrice();
        return formatNumbers(sum) + " SEK";
    }

    public String formattedQuantity() {
        return formatNumbers(getQuantity());
    }

    private String formatNumbers(int number) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("sv","SE"));
        String formmated = nf.format(number);
        return formmated.substring(0,formmated.lastIndexOf(","));
    }

    public OrderHistory getOrder() {
        return order;
    }

    public void setOrder(OrderHistory order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

}
