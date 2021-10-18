package org.c130.vendingmachine.dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private int qty;
    private BigDecimal cost;

    public Item(String name, int qty, BigDecimal cost) {
        this.name = name;
        this.qty = qty;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
