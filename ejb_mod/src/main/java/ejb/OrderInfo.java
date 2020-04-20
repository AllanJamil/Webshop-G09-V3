package ejb;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Record record;
    private int quantity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Order order;

    public OrderInfo(Record record, int quantity) {
        this.record = record;
        this.quantity = quantity;
    }

    public OrderInfo() {

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
    public int getTotalPrice() {
        return quantity * record.getPrice();
    }
}
