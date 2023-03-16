package com.inditext.prices.infra.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Currency;
import java.util.Date;

@Entity
@Table(name="prices")
public class H2Price {
    @Id
    @Column(name="price_list")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "brand_id", nullable = false)
    private long brandId;
    @NotNull
    @Column(name = "start_date", nullable = false)
    private String start;
    @NotNull
    @Column(name = "end_date", nullable = false)
    private String end;
    @Column(name = "product_id", nullable = false)
    private long productId;
    @Column(name = "priority", nullable = false)
    private int priority;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "curr", nullable = false)
    private Currency curr;


    public H2Price(long id, long brandId, String start, String end, long productId, int priority, double price, Currency curr) {
        this.id = id;
        this.brandId = brandId;
        this.start = start;
        this.end = end;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public H2Price() {
    }

    public long getId() {
        return id;
    }

    public long getBrandId() {
        return brandId;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
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
