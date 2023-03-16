package com.inditext.prices.domain;

import java.util.Currency;
import java.util.Date;

public class Price {
    private final long id;
    private final long brandId;
    private final Date start;
    private final Date end;
    private final long productId;
    private final int priority;
    private final double price;
    private final Currency curr;

    public Price(long id, long brandId, Date start, Date end, long productId, int priority, double price, Currency curr) {
        this.id = id;
        this.brandId = brandId;
        this.start = start;
        this.end = end;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public long getId() {
        return id;
    }

    public long getBrandId() {
        return brandId;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public long getProductId() {
        return productId;
    }

    public int getPriority() {
        return priority;
    }

    public double getPrice() {
        return price;
    }

    public Currency getCurr() {
        return curr;
    }

}
