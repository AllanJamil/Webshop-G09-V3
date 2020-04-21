package entity;

import javax.persistence.*;
import java.io.Serializable;

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
    public int getTotalPrice() {
        return quantity * record.getPrice();
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
