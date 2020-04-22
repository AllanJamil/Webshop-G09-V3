package controller;

import ejb.Record;

public class CartItem {

    private Record record;
    private int qty;

    public CartItem(Record record) {
        this.record = record;
        this.qty = 1;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotalPrice() {
        return record.getPrice() * qty;
    }
}
